 /**
 * Created by xiaodongliang on 2/9/18.
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


import com.autodesk.client.ApiException;
import com.autodesk.client.ApiResponse;
import com.autodesk.client.api.BucketsApi;
import com.autodesk.client.api.DerivativesApi;
import com.autodesk.client.api.ObjectsApi;
import com.autodesk.client.auth.Credentials;
import com.autodesk.client.auth.OAuth2TwoLegged;
import com.autodesk.client.model.*;
import org.apache.commons.codec.binary.Base64;

//import javax.ws.rs.core.UriBuilder;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.*;


@WebServlet("/HelloServer")
public class HelloServer extends HttpServlet {
    private String message;


    //when enviroment variables or Tomcat variables are empty, use the hard-coded values 
    private static String FORGE_CLIENT_ID = "<your forge client id>";
    private static String FORGE_CLIENT_SECRET="<your forge client secret>";
    private static String FORGE_TEST_URN="<your forge test urn> (with urn:)";

    private static OAuth2TwoLegged oauth2TwoLegged;
    private static Credentials twoLeggedCredentials;

    @Override
    public void init() throws ServletException {
        try
        {
            initializeOAuth();
        }
        catch(Exception ex){

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        String token = twoLeggedCredentials.getAccessToken();

        JSONObject obj = new JSONObject();
        obj.put("token",token);
        obj.put("urn",FORGE_TEST_URN);

        out.println(obj.toString());
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private String getEnvVar(String varName){

        String returnV = "";

        //on AWS EBS, get properties from Tomcat enviroment
        String temp= System.getProperty(varName);
        if(temp!=null && !temp.isEmpty()) {
            returnV = temp;
        }
        else{
            //local test, get enviroment variables
            returnV= System.getenv(varName);

            //when no enviroment variables, will use the manual input values 
        }
        return returnV;
    }

    private void initializeOAuth() throws Exception {

        String temp = getEnvVar("FORGE_CLIENT_ID"); 
        FORGE_CLIENT_ID = temp.isEmpty()?FORGE_CLIENT_ID:temp;
        
        temp = getEnvVar("FORGE_CLIENT_SECRET"); 
        FORGE_CLIENT_SECRET = temp.isEmpty()?FORGE_CLIENT_SECRET:temp;
         
        temp = getEnvVar("FORGE_TEST_URN"); 
        FORGE_TEST_URN = temp.isEmpty()?FORGE_TEST_URN:temp;

        // You must provide at least one valid scope
        List<String> scopes = new ArrayList<String>();
        scopes.add("data:read");
        scopes.add("data:write");
        scopes.add("bucket:create");
        scopes.add("bucket:read");

        //Set autoRefresh to `true` to automatically refresh the access token when it expires.
        oauth2TwoLegged = new OAuth2TwoLegged(FORGE_CLIENT_ID, FORGE_CLIENT_SECRET, scopes, true);
        twoLeggedCredentials = oauth2TwoLegged.authenticate();
    }
}
