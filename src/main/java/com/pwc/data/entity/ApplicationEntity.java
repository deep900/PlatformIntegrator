/**
 * 
 */
package com.pwc.data.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Pradheep
 *
 */
@Entity
@Table(name="application")
@Getter
@Setter
public class ApplicationEntity {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="application_name")
	private String applicationName;
	
	@Column(name="api_definitions")
	private Blob apiDefinition;
	
	@Column(name="http_url")
	private String httpUrl;
	
	@Column(name="https_url")
	private String httpsUrl;
	
	@Column(name="auth_url")
	private String authUrl;
	
	@Column(name="content_type")
	private String contentType;	
	
	@Column(name="executable_file_path")
	private String executableFilePath;
	
	@Column(name="executable_port")
	private int executablePort;
	
}
