
package edu.cmu.cs.ebiz.teamHEX.task8.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

import edu.cmu.cs.ebiz.teamHEX.task8.model.Model;

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

	public String transform(String str) {
		if (str == null || str.trim().length() == 0) return "";
		
		str = str.trim();
		if (str.indexOf(',') != -1) {
			str = str.substring(0, str.indexOf(','));
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(str.charAt(0)));

		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) != ' ') {
				if (str.charAt(i - 1) == ' ') {
					sb.append(' ');
					sb.append(Character.toUpperCase(str.charAt(i)));
				} else {
					sb.append(Character.toLowerCase(str.charAt(i)));
				}
			}
		}
		return sb.toString();
	}
	
	public void setCities1(String v) {
		cities1 = transform(v);
	}

	public void setCities2(String v) {
		cities2 = transform(v);
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
		local = transform(v);
	}
	
	public void setAction(String v) {
		this.action = v;
	}
	
	public boolean isPresent() {
		return action != null;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (!action.equals("compare") && !action.equals("change")) 
			errors.add("Invalid button");
		
		if (errors.size() > 0)
			return errors;
		
		if (action.equals("compare"))  {
			if ((cities1 == null || cities1.length() == 0) || (cities2 == null || cities2.length() == 0)) {
				errors.add("Both cities are required");
			}
		
			if (!Model.isCityExist(cities1) || !Model.isCityExist(cities2)) {
				errors.add("Whoa! That's not a city. Try again");
			}
			
			if ( (sports == null || sports.length() == 0) && (restaurants == null || restaurants.length() == 0) 
					&& (employment == null || employment.length() == 0)	&& (celebrity == null || celebrity.length() == 0) 
					&& (education == null || education.length() == 0) && (crime == null || crime.length() == 0)){
				errors.add("At least one catogory for comparison is required");
			}
			if (action == null) {
				errors.add("Button is required");
			}
		} else {
			if ((local == null || local.length() == 0)) {
				errors.add("Your city name is required");
			}
		}

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