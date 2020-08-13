
/**
* The Country class will store information about a country and provide methods to
* get,set data and compare the Country objects by Name, Happiness Rank,GDP per capita 
* and print user desired Country with desired attributes.
*
* @author Angel A. Selva Rodriguez
* @version 06/27/2020
*/
;

public class Country {

	private String name, code, capitol;
	private Integer population, happyRank;
	private double GDP;

	/**
	 * Empty constructor, usually implicitly defined but per documentation should be
	 * made explicit. No parameters Does not return anything
	 */
	public Country() {
	}// end empty constructor

	/**
	 * This constructor method of Country, initializes Country objects from the
	 * given input file. The parameters for this method are the following: parameter
	 * 1: Description of the purpose of the method, the meaning of the input
	 * parameters (if any) and the meaning of the return values (if any).
	 *
	 * @param name       describes name of the country object - Type String
	 * @param code       gives the country code of the country object - Type String
	 * @param capitol    is used for the capitol of the country object - Type String
	 * @param population is used to describe the size of the country object - Type
	 *                   String
	 * @param GDP        used for describing wealth of the country object - Type
	 *                   String
	 * @param happyRank  for describing rank of a country object - Type String
	 * @return initialized country object, allowing access to private parameters
	 */
	public Country(String country, String countryCode, String countryCapitol, String countryPopulation,
			String countryGDP, String countryHappyRank) {
		name = country;
		code = countryCode;
		capitol = countryCapitol;
		population = (Integer) Integer.parseInt(countryPopulation);
		GDP = (double) Double.parseDouble(countryGDP);
		happyRank = (Integer) Integer.parseInt(countryHappyRank);

	}// end Country constructor with private parameters

	/**
	 * The method allows access to private variables of country object In this case,
	 * it returns the name received from setName.
	 * 
	 * @return name of country object
	 */
	public String getName() {
		return name;
	}

	/**
	 * The method allows access to private variables of country object This method
	 * will set the name given to the country object and assign to variable name,
	 * used by get method.
	 * 
	 * @param name of country object
	 * @return name of this specific country object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The method allows access to private variables of country object In this case,
	 * it returns the code received from setCode.
	 * 
	 * @return code of country object
	 */
	public String getCode() {
		return code;
	}

	/**
	 * The method allows access to private variables of country object This method
	 * will set the code given to the country object and assign to variable code,
	 * used by get method.
	 * 
	 * @param code of country object
	 * @return code of this specific country object
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * The method allows access to private variables of country object In this case,
	 * it returns the capitol received from setCapitol.
	 * 
	 * @return capitol of country object
	 */
	public String getCapitol() {
		return capitol;
	}

	/**
	 * The method allows access to private variables of country object This method
	 * will set the capitol given to the country object and assign to variable
	 * capitol, used by get method.
	 * 
	 * @param capitol of country object
	 * @return capitol of this specific country object
	 */
	public void setCapitol(String capitol) {
		this.capitol = capitol;
	}

	/**
	 * The method allows access to private variables of country object In this case,
	 * it returns the population received from setPopulation.
	 * 
	 * @return population of country object
	 */
	public Integer getPopulation() {
		return population;
	}

	/**
	 * The method allows access to private variables of country object This method
	 * will set the population given to the country object and assign to variable
	 * population, used by get method.
	 * 
	 * @param population of country object
	 * @return population of this specific country object
	 */
	public void setPopulation(Integer population) {
		this.population = population;
	}

	/**
	 * The method allows access to private variables of country object In this case,
	 * it returns the GDP received from setGDP
	 * 
	 * @return GDP of country object
	 */

	public Double getGDP() {
		return GDP;
	}

	/**
	 * The method allows access to private variables of country object This method
	 * will set the GDP given to the country object and assign to variable GDP, used
	 * by get method.
	 * 
	 * @param GDP of country object
	 * @return GDP of this specific country object
	 */
	public void setGDP(Double GDP) {
		this.GDP = GDP;
	}

	/**
	 * The method allows access to private variables of country object In this case,
	 * it returns the happyRank received from setHappyRank
	 * 
	 * @return happyRank of country object
	 */
	public Integer getHappyRank() {
		return happyRank;
	}

	/**
	 * The method allows access to private variables of country object This method
	 * will set the happyRank given to the country object and assign to variable
	 * happyRank, used by get method.
	 * 
	 * @param happyRank of country object
	 * @return happyRank of this specific country object
	 */
	public void setHappyRank(Integer happyRank) {
		this.happyRank = happyRank;
	}

	public int compareTo(Country country) {
		if (this.getName().compareToIgnoreCase(country.getName()) < 0)
			return -1;
		else if (this.getName().compareToIgnoreCase(country.getName()) == 0)
			return 0;
		else {
			if (this.getName().compareToIgnoreCase(country.getName()) > 0)
				return 1;
		}
		return -9999;

	}

	@Override
	public String toString() {
		return " " + this.getName() + " " + this.getCode() + " " + this.getCapitol() + " " + this.getPopulation() + " "
				+ this.getGDP() + " " + this.getHappyRank();
	}

}// END COUNTRY CLASS
