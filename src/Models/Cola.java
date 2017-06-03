package Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import Exceptions.ColaExcededSizeException;
import Exceptions.ElementBlockedException;
import Exceptions.LlevateTuNullDeAquiException;

public class Cola<E> extends ArrayList<E> {
	private Comparator<E> comparador;
	private final int MAX_SIZE = 10;

	public Cola(Comparator<E> comparador) {
		super();
		this.comparador = comparador;
	}

	@Override
	public boolean add(E elemento) throws ColaExcededSizeException, LlevateTuNullDeAquiException {

		if (elemento != null) {
			if (super.size() < MAX_SIZE) {
				boolean esCorrecto = super.add(elemento);
				super.sort(comparador);
				return esCorrecto;
			} else {
				throw new ColaExcededSizeException("Lista llena!");
			}
		} else {
			throw new LlevateTuNullDeAquiException("Objeto nulo no valido");
		}

	}
	
	@Override
	public boolean addAll(Collection<? extends E> coleccion)throws ColaExcededSizeException, LlevateTuNullDeAquiException{
		if((super.size()+coleccion.size())<=MAX_SIZE){
			if(!coleccion.contains(null)){
				return super.addAll(coleccion);
			}else{
				throw new LlevateTuNullDeAquiException("Contiene null.");
			}
		}else{
			throw new ColaExcededSizeException("Superado tamaño maximo de cola.");
		}
	}

	@Override
	public E remove(int index) throws ElementBlockedException {
		if (super.size() > 1) {
			E auxE =super.remove(index);
			super.sort(comparador);
			return auxE;
		} else {
			throw new ElementBlockedException("En la cola siempre tiene que quedar un elemento.");
		}
	}

	@Override
	public void clear() {
		throw new ElementBlockedException("En la cola siempre tiene que quedar un elemento.");
	}

}
