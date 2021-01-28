package com.alumne.calculadora;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Calculadora {

	// Constants
	final int MAX_DIGITS = 5;
	final int MODE_ENTRADA = 0;
	final int MODE_RESULTAT = 1;

	//Variables
	int mode;
	int valor1;
	int valor2;
	String operacio;
	boolean inicialitza_resultat;

	//private static String text_resultat;

	protected Shell shell;
	private static Text text_resultat;

	//Constructor de la classe calculadora amb par�metre

	public Calculadora(boolean gui) {

		//Inicialitzaci� de les variables.
		inicialitza();

		if (gui==true) dibuixaCalculadora();
	}

	//Codi autom�tic per a pintar la GUI

	public static void main(String[] args) {
		try {
			//Normalment executarem la calculadora en mode grafic (true) pero per a depuracio la usarem en mode terminal (false)
			Calculadora window = new Calculadora(true);
			//El codi generat automaticament usa el metode window.open() en comptes de dibuixaCalculadora
			//window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Dibuixar la finestra
	//El codi generat automaticament usa el metode window.open() en comptes de dibuixaCalculadora
	//public void open() {
	private void dibuixaCalculadora() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	//Creaci� dels elements que posarem a la finestra
	protected void createContents() {
		shell = new Shell();
		//shell.setSize(450, 300);
		shell.setSize(259, 250);
		shell.setText("Calculadora");

		//-------------------------------------------------
		//N�meros
		//-------------------------------------------------

		Button button_0 = new Button(shell, SWT.NONE);
		button_0.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				afegeixNouDigit(0);
			}
		});
		button_0.setBounds(23, 163, 40, 33);
		button_0.setText("0");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				afegeixNouDigit(1);
			}
		});
		button_1.setBounds(23, 124, 40, 33);
		button_1.setText("1");

		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				afegeixNouDigit(2);
			}

		});
		button_2.setText("2");
		button_2.setBounds(69, 124, 40, 33);

		//but� amb el n�mero 3
		Button button_3 = new Button(shell, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				afegeixNouDigit(3);
			}
		});
		button_3.setText("3");
		button_3.setBounds(115, 124, 40, 33);

		//but� amb el n�mero 4
		Button button_4 = new Button(shell, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				afegeixNouDigit(4);
			}
		});
		button_4.setText("4");
		button_4.setBounds(23, 85, 40, 33);

		//but� amb el n�mero 5
		Button button_5 = new Button(shell, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				afegeixNouDigit(5);
			}
		});
		button_5.setText("5");
		button_5.setBounds(69, 85, 40, 33);

		//but� amb el n�mero 6
		Button button_6 = new Button(shell, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				afegeixNouDigit(6);
			}
		});
		button_6.setText("6");
		button_6.setBounds(115, 85, 40, 33);

		//but� amb el n�mero 7
		Button button_7 = new Button(shell, SWT.NONE);
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				afegeixNouDigit(7);
			}
		});
		button_7.setText("7");
		button_7.setBounds(23, 46, 40, 33);

		//but� amb el n�mero 8
		Button button_8 = new Button(shell, SWT.NONE);
		button_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				afegeixNouDigit(8);
			}
		});
		button_8.setBounds(69, 46, 40, 33);
		button_8.setText("8");


		//but� amb el n�mero 9
		Button button_9 = new Button(shell, SWT.NONE);
		button_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				afegeixNouDigit(9);
			}
		});
		button_9.setText("9");
		button_9.setBounds(115, 46, 40, 33);

		//-------------------------------------------------
		//Operacions
		//-------------------------------------------------

		//but� amb l'operaci� de divisi�
		Button button_12 = new Button(shell, SWT.NONE);
		button_12.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executarOperador("/");
			}
		});
		button_12.setText("/");
		button_12.setBounds(178, 46, 40, 33);

		//but� amb l'operaci� de multiplicaci�
		Button button_13 = new Button(shell, SWT.NONE);
		button_13.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executarOperador("*");
			}
		});
		button_13.setText("*");
		button_13.setBounds(178, 85, 40, 33);

		//but� amb l'operaci� de suma
		Button button_14 = new Button(shell, SWT.NONE);
		button_14.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executarOperador("+");
			}
		});
		button_14.setText("+");
		button_14.setBounds(178, 124, 40, 33);

		//but� amb l'operaci� de resta
		Button button_15 = new Button(shell, SWT.NONE);
		button_15.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executarOperador("-");
			}
		});
		button_15.setText("-");
		button_15.setBounds(178, 163, 40, 33);

		//but� amb l'operaci� de igual
		Button button_11 = new Button(shell, SWT.NONE);
		button_11.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executarIgual();
			}
		});
		button_11.setText("=");
		button_11.setBounds(69, 163, 86, 33);

		//Text on es visualitza el resultat
		text_resultat = new Text(shell, SWT.BORDER);
		text_resultat.setText("0");
		text_resultat.setBounds(22, 19, 196, 21);

	}
	
	/*
	 *Inicialitza les variables que s'utilitzaran en el programa per fer les operacións entre els dos enters
	 */

	public void inicialitza()  {
		operacio = "null";
		valor1 = 0;
		valor2 = 0;
		mode = MODE_ENTRADA;
		inicialitza_resultat = true;
	}
	
	/*
	 *Llegeix el resultat com a string
	 */

	public String getResultatString (){
		return text_resultat.getText();
	}
	
	/*
	 *Converteix el enter per a que es mostre en pantalla
	 */

	public void setResultatString(String s){
		text_resultat.setText(s);
	}
	
	/*
	 *Llegeix el string com a resultat el converteix a enter
	 */

	public int getResultatInt()  {
		String resultat = text_resultat.getText();
		return Integer.parseInt(resultat);
	}

	public void afegeixNouDigit(int digit){
		if (inicialitza_resultat)
			setResultatString("");

		String inputString = getResultatString();

		//Elimina repeticio de zeros inicial
		if (inputString.indexOf("0") == 0){
			inputString = inputString.substring(1);
		}

		//Concatena el nou digit amb el que teniem
		if ((!inputString.equals("0") || digit > 0)  && inputString.length() < MAX_DIGITS){
			setResultatString(inputString + digit);
		}

		mode = MODE_ENTRADA;
		inicialitza_resultat = false;
	}

	public void executarOperador(String new_operacio) {

		int resultat;
		inicialitza_resultat = true;
		operacio = new_operacio;

		if (operacio.equals("null"))  
		{
			resultat = getResultatInt();
			valor1 = resultat;
		}
		else
		{
			valor2 = getResultatInt();
			resultat = executarOperacio();
			mostraResultat(resultat);
			valor1 = resultat;     
		}
	}

	public void executarIgual(){
		int resultat = 0;

		valor2 = getResultatInt();
		resultat = executarOperacio();
		mostraResultat(resultat);

		operacio = "null";
	} 

	public int executarOperacio() {
		int resultat = 0;

		if (operacio.equals("/")){

			//Comentar if i else ...
			if (valor2 == 0) {
				JOptionPane.showMessageDialog(null, "No es pot dividir per cero", "Error", JOptionPane.ERROR_MESSAGE);
				operacio = "null";
				valor1 = 0;
				mode = MODE_ENTRADA;
				inicialitza_resultat = true;
			} else {
				resultat = valor1 / valor2;
			}
			//... i descomentar aquesta part per a produir l'excepci�
			//resultat = valor1 / valor2;
		}

		if (operacio.equals("*"))
			resultat = valor1 * valor2;

		if (operacio.equals("-"))
			resultat = valor1 - valor2;

		if (operacio.equals("+"))
			resultat = valor1 + valor2;

		return resultat;
	}

	public void mostraResultat(int resultat){
		setResultatString(Integer.toString(resultat));
		valor1 = resultat;
		mode = MODE_RESULTAT;
		inicialitza_resultat = true;
	}
}