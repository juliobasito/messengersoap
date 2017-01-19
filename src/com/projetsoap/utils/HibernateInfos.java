/**
 * Transaction de données générales dans le projet
 */
package com.projetsoap.utils;

public class HibernateInfos {

	public static final String configFile = "hibernate.cfg.xml";

	public static String getConfigfile() {
		//Renvoie le nom du fichier de config hibernate
		return configFile;
	}

}
