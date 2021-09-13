/**
 * 
 */
package com.pwc.data.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="application_name")
	private String appname;
	
	@Column(name="api_definitions")
	private String openAPIDefinitionUrl;
	
	@Column(name="http_url")
	private String httpUrl;
	
	@Column(name="https_url")
	private String httpsUrl;
	
	@Column(name="auth_url")
	private String authUrl;
	
	@Column(name="content_type")
	private String contentType;	
	
	@JsonIgnore
	@Column(name="open_api_file")
	private Blob openAPIDefinitionFile;
	
	@Column(name="executable_port")
	private int executablePort;
	
	@Column(name="status")
	private String status;
	
	
}
