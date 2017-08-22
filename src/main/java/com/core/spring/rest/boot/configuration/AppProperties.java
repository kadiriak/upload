package com.core.spring.rest.boot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class AppProperties {

	private Upload upload = new Upload();

	public static class Upload {

		private String location;
		private String dataFile;

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getDataFile() {
			return dataFile;
		}

		public void setDataFile(String dataFile) {
			this.dataFile = dataFile;
		}

		@Override
		public String toString() {
			return "Upload [location=" + location + ", dataFile=" + dataFile + "]";
		}

	}

	public Upload getUpload() {
		return upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	@Override
	public String toString() {
		return "AppProperties [upload=" + upload + "]";
	}

}
