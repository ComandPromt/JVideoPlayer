package com.views;

import java.io.Serializable;

@SuppressWarnings("serial")

class Objeto implements Serializable {

	private String _dato;

	public Objeto(String dato) {

		this._dato = dato;

	}

	@Override
	public String toString() {

		return this._dato;

	}

}