package tp2;

import java.io.*;
import java.net.*;

public class GetHTTP
{
	protected URL url;
	protected HttpURLConnection server;

	/**
	 * @param szUrl: String object for the URL
	 */
	public GetHTTP(String szUrl) throws Exception
	{
		try
		{
			url = new URL(szUrl);
		}
		catch (Exception e)
		{
			throw new Exception("Invalid URL");
		}
	}

	/**
	 * @param method: String object for client method (POST, GET,...)
	 */
	public void connect(String method) throws Exception
	{
		try
		{
			server = (HttpURLConnection)url.openConnection();
			server.setDoInput(true);
			server.setDoOutput(true);
			server.setRequestMethod(method);
			server.setRequestProperty("Content-type",
					"application/x-www-form-urlencoded");
			server.connect();
		}
		catch (Exception e)
		{
			throw new Exception("Connection failed");
		}
	}

	public void disconnect()
	{
		server.disconnect();
	}

	public void displayResponse() throws Exception
	{
		String line;

		try
		{
			BufferedReader s = new BufferedReader(
					new InputStreamReader(
							server.getInputStream()));
			line = s.readLine();
			while (line != null)
			{
				System.out.println(line);
				line = s.readLine();
			}
			s.close();
		}
		catch(Exception e)
		{
			throw new Exception("Unable to read input stream");
		}
	}


	public static void main(String argv[])
	{


		try
		{
			GetHTTP c = new GetHTTP("http://localhost/index.html");
			c.connect("GET");
			c.displayResponse();
			c.disconnect();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

