package com.Controller;
/*
Controller is responsible to fetch data from view part (jsp pages)
and than Controller transfer these data to model part (dao) 
with the help of bean class object 

*******************


String WebURI="http://localhost:8080/RestServer/";
-->url for server 

ClientConfig clientConfig=new ClientConfig();
-->First point the ClientConfig isn't mandatory to create new Jersey client.With ClientConfig you can have some benefits:	
-->You can register Filters through ClientConfigs to Jersey Client instance to use, so with Filters you can intercept the requests or responses and modify them.
-->Register provider classes,for example, that support JAXB with JSON.
-->You can set properties to the client through ClientConfig for example,PROPERTY_FOLLOW_REDIRECTS to configure Jersey Client to automatically redirect to URI on 3XX HTTP response codes.

The client configuration that declares common property names, features, properties, provider classes and singleton instances that may be used by a Client instance.

Client client=null;
The main class for creating WebResource instances and configuring the properties of connections and requests.
A client may be configured by passing a ClientConfig instance to the appropriate constructor.

WebTarget webTarget=null;
WebTarget interface represents a specific URI you want to invoke on
A resource target identified by the resource URI.


Gson gson=new Gson();
Google Gson for converting Java objects to JSON and JSON to Java
What is Gson
Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object.

gson.toJson(s);//serializes/convert [ s ] to Json [sreanibadha]


Response rs=webTarget.path("student").path("insert").request().post(Entity.json(student));

Response 
Defines the contract between a returned instance and the runtime when an application needs to provide meta-data to the runtime.

webTarget.path("student").path("insert")
path(String path)
Create a new WebTarget instance by appending path to the URI of the current target instance.

request()
Start building a request to the targeted web resource.

post(Entity.json(student));
post(Entity<?> entity)
Invoke HTTP POST method for the current request synchronously.



rs.readEntity(String.class);
readEntity(Class<T> entityType)
Read the message entity input stream as an instance of specified Java type using a MessageBodyReader that supports mapping the message entity stream onto the requested type.




*/
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.bean.StudentBean;
import com.google.gson.Gson;

@WebServlet("/ActionController")
public class ActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String WebURL = "http://localhost:8080/RestServer/";
	ClientConfig clientconfig = null;
	Client client = null;
	WebTarget target = null;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Do Process Method Called");
		clientconfig=new ClientConfig();
		client=ClientBuilder.newClient(clientconfig);
		target=client.target(WebURL).path("rest");
		
		String action=request.getParameter("action");
		
		if(action.equalsIgnoreCase("insert"))
		{
			System.out.println("Insert button Clicked");
			StudentBean s=new StudentBean();
			s.setFname(request.getParameter("fname"));
			s.setLname(request.getParameter("lname"));
			s.setEmail(request.getParameter("email"));
			
			String student=gson.toJson(s);
			
			Response rs=target.path("student").path("insert").request().post(Entity.json(student));
			String result=rs.readEntity(String.class);
			System.out.println(result);
			
		}

	}
}
