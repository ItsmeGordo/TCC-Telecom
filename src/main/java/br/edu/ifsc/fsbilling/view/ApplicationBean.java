package br.edu.ifsc.fsbilling.view;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("applicationBean")
@ApplicationScoped
public class ApplicationBean {

	private int autocompleteMinQueryLength = 3;
	private int autocompleteQueryDelay = 1250;

	public int getAutocompleteMinQueryLength() {
		return autocompleteMinQueryLength;
	}

	public int getAutocompleteQueryDelay() {
		return autocompleteQueryDelay;
	}
}
