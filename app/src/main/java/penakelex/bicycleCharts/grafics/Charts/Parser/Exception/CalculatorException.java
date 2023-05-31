package penakelex.bicycleCharts.grafics.Charts.Parser.Exception;

/** CalculatorException
 *  	Класс-исключение
 * */
public class CalculatorException extends Exception {

	private static final long serialVersionUID = 6235428117353457356L;

	/** CalculatorException
	 * 		Пустой конструктор класса
	 * */
	public CalculatorException() {
		super();
	}

	/** CalculatorException
	 * 		Конструктор класса
	 *	Вход:
	 *		String message - сообщение
	 * */
	public CalculatorException(final String message) {
		super(message);
	}
}
