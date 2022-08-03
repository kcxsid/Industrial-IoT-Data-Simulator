package com.ea;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.ea.datagen.DataSimulator;

public class DataSimulatorMain {

	public static void main(String[] args) {

		DataSimulator simulator = new DataSimulator();
		try {
			simulator.submit();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
