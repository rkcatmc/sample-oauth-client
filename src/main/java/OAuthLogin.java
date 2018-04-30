import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class OAuthLogin {
	
	static final String CLIENT_ID = "XXXXXXXXXXXXXX";
	static final String CLIENT_SECRET = "XXXXXXXXXXXXXX";

	private static TrustManager[] disableCertificates() {
	    return new TrustManager[]{
	        new X509TrustManager() {

	            @Override
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }

	            @Override
	            public void checkClientTrusted(
	                    java.security.cert.X509Certificate[] certs, String authType) {
	            }

	            @Override
	            public void checkServerTrusted(
	                    java.security.cert.X509Certificate[] certs, String authType) {
	            }
	        }
	    };
	}
	
	/** Authorizes the installed application to access user's protected data. */
	 private static TokenResponse authorize() throws Exception {
		 JsonFactory jsonFactory = new JacksonFactory();
		 HttpTransport httpTransport = new NetHttpTransport();
		  
		 AuthorizationCodeFlow flow = new AuthorizationCodeFlow.Builder(
				    BearerToken.authorizationHeaderAccessMethod(),
				    httpTransport, jsonFactory,
				    new GenericUrl("https://github.com/login/oauth/access_token"),
				    new ClientParametersAuthentication( CLIENT_ID, CLIENT_SECRET ),
				    CLIENT_ID,
				    "https://github.com/login/oauth/authorize").build();
				 
				TokenResponse tokenResponse = flow
				    .newTokenRequest("AUTH CODE?")
				    .setScopes(Collections.singletonList("user:email"))
				    .setRequestInitializer(new HttpRequestInitializer() {
				        @Override
				        public void initialize(HttpRequest request) throws IOException {
				            request.getHeaders().setAccept("application/json");
				        }
				    }).execute();
				
				return tokenResponse;
}
	 public static void main(String argv[]) throws Exception {
		 
	    // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, disableCertificates(), new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        TokenResponse resp = authorize();
		 System.out.println(resp.getAccessToken());
	 }
}
