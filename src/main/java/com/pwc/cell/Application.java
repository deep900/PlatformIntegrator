/**
 * 
 */
package com.pwc.cell;

import java.util.List;

/**
 * @author Pradheep
 *
 */
public abstract class Application {
	
	public String name;
	
	public String httpUrl;
	
	public String httpsUrl;
	
	public String authUrl;
	
	public List<String> contentTypes;	
	
	/**
	 * Optional path for executable file. 
	 * This is not necessary if httpUrl exists.
	 * Applicable for Jar files.
	 */
	public String runnableFileAbsPath;
	
	/**
	 * Optional port for executable to run. 
	 * This is not necessary if httpUrl exists.
	 * Applicable for jar files.
	 */
	public int runnablePort;
	
	/**
	 * Optional.
	 */
	public String dockerImageFile;
	
	/**
	 * This will be used in creation of API in APIManager.
	 */
	public String openAPIDefinitionUrl;
	
	public String openAPIDefinitionFile;
	
	public String status;
}
