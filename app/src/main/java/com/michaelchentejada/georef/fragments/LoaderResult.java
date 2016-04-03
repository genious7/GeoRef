package com.michaelchentejada.georef.fragments;

/**
 * <p>Helper class for storing the result of an AsyncLoader</p>
 * Created by Michael Chen on 04/02/2016.
 */
public class LoaderResult<T> {

	private final T mResult;

	private LoaderState mState;

	public LoaderResult(T result, LoaderState state) {
		mResult = result;
		mState = state;
	}

	public LoaderState getState() {
		return mState;
	}

	public T getResult(){
		return mResult;
	}

	public enum LoaderState {
		SUCCESS,
		ERROR_INTERNET,
		LOADING
	}
}
