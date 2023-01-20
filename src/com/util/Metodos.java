package com.util;

import java.io.File;

import util.Mthos;

public class Metodos {

	public static boolean esVideo(String name) {

		boolean resultado = false;

		name = name.toLowerCase();

		if (name.endsWith(".mp4") || name.endsWith(".avi") || name.endsWith(".mpg") || name.endsWith(".mkv")) {

			resultado = true;

		}

		return resultado;

	}

	public static void crearCarpeta() {

		try {

			String ruta = new File(".").getCanonicalPath() + Mthos.saberSeparador() + "log";

			File dir = new File(ruta);

			if (!dir.exists()) {

				dir.mkdir();

			}

			File file = new File(ruta + Mthos.saberSeparador() + "output.txt");
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			file = new File(ruta + Mthos.saberSeparador() + "output1.txt");
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
