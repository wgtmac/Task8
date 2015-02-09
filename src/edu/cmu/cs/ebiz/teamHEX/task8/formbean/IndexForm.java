
package edu.cmu.cs.ebiz.teamHEX.task8.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class IndexForm extends FormBean {
	private String cities1;
	private String cities2;
	private String sports;
	private String restaurants;
	private String employment;
	private String celebrity;
	private String education;
	private String crime;
	private String local;
	private String action;

	public String getCities1() {
		return cities1;
	}

	public String getCities2() {
		return cities2;
	}

	public String getSports() {
		return sports;
	}

	public String getRestaurants() {
		return restaurants;
	}

	public String getEmployment() {
		return employment;
	}

	public String getCelebrity() {
		return celebrity;
	}

	public String getEducation() {
		return education;
	}

	public String getCrime() {
		return crime;
	}

	public String getLocal() {
		return local;
	}
	
	public String getAction() {
		return action;
	}

	public void setCities1(String v) {
		cities1 = v;
		if (cities1 != null) {
			v = v.trim();
			if (v.indexOf(',') != -1) {
				cities1 =  v.substring(0, v.indexOf(','));
			}
		} 
	}

	public void setCities2(String v) {
		this.cities2 = v;
		if (cities2 != null) {
			v = v.trim();
			if (v.indexOf(',') != -1) {
				cities2 =  v.substring(0, v.indexOf(','));
			}
		} 
	}

	public void setSports(String v) {
		this.sports = v;
	}

	public void setRestaurants(String v) {
		this.restaurants = v;
	}

	public void setEmployment(String v) {
		this.employment = v;
	}

	public void setCelebrity(String v) {
		this.celebrity = v;
	}

	public void setEducation(String v) {
		this.education = v;
	}

	public void setCrime(String v) {
		this.crime = v;
	}
	
	public void setLocal(String v) {
		this.local = v;
		if (local != null) {
			v = v.trim();
			if (v.indexOf(',') != -1) {
				local =  v.substring(0, v.indexOf(','));
			}
		} 
	}
	
	public void setAction(String v) {
		this.action = v;
	}
	
	public boolean isPresent() {
		return action != null;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if ((cities1 == null || cities1.length() == 0) || (cities2 == null || cities2.length() == 0)) {
			errors.add("Both cities are required");
		}
		if ( (sports == null || sports.length() == 0) && (restaurants == null || restaurants.length() == 0) 
				&& (employment == null || employment.length() == 0)	&& (celebrity == null || celebrity.length() == 0) 
				&& (education == null || education.length() == 0) && (crime == null || crime.length() == 0)){
			errors.add("At least one catogory for comparison is required");
		}
		if (action == null) {
			errors.add("Button is required");
		}
		
		if (errors.size() > 0)
			return errors;
		
        if (!action.equals("compare") && !action.equals("change")) errors.add("Invalid button");

		return errors;
	}
	
	public List<String> getComparisonList() {
		List<String> ret = new ArrayList<String>();
		if ((sports != null && sports.length() != 0)) {
			ret.add("sports");
		}
		if ((restaurants != null && restaurants.length() != 0)) {
			ret.add("restaurants");
		}
		if ((employment != null && employment.length() != 0)) {
			ret.add("employment");
		}
		if ((celebrity != null && celebrity.length() != 0)) {
			ret.add("celebrity");
		}
		if ((education != null && education.length() != 0)) {
			ret.add("education");
		}
		if ((crime != null && crime.length() != 0)) {
			ret.add("crime");
		}
		return ret;
	}

}