/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicatextos;
import java.io.*;
import java.util.*;

/**
 *
 * @author 9alej
 */
public class PracticaTextos {
	public static void main(String[] args) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
				//el archivo debe estar en la razi y pedir por pantalla solo el nombre =))))))
		archivo = new File ("quijoteCap1.txt");
		double inicioKM=0.0;
		double finalKM=0.0;
		double totalKM=0.0;
		
		double inicioBM=0.0;
		double finalBM=0.0;
		double totalBM=0.0;
		
		try {
			ArrayList<Integer> incidenciasKnuthMorris=null;
			ArrayList<Integer> incidenciasBoyer=null;
			double numeroIncidenciasKnuthMorris=0;
			double numeroIncidenciasBoyer=0;
			fr = new FileReader (archivo);
					
			br = new BufferedReader(fr);
			
			String linea;
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Introduzca el patron a buscar: ");
			String patron =sc.nextLine();
			
			System.out.println("Introduzca el porcentaje de texto en el que buscar: ");
			double porcentaje=sc.nextDouble();
			
			sc.close();
			
			double lineas = Math.ceil((100/porcentaje));
			
			while((linea=br.readLine())!=null){
				
				inicioKM=System.currentTimeMillis();
				incidenciasKnuthMorris=AnalizaTextos.KnuthMorrisPrat(patron, linea); //usar mas algoritmos para dejarlo mas bonito, con mediciones de tiempo
				numeroIncidenciasKnuthMorris+=incidenciasKnuthMorris.size();
				finalKM=System.currentTimeMillis();
				totalKM+=(finalKM-inicioKM);
				
				inicioBM=System.currentTimeMillis();
				incidenciasBoyer = AnalizaTextos.BoyerMoore(patron, linea);
				numeroIncidenciasBoyer+=incidenciasBoyer.size();
				finalBM=System.currentTimeMillis();
				totalKM+=(finalBM-inicioBM);
				
				for(int i=0; i<(lineas-1);i++){
					linea=br.readLine();
				}
				
				
			}

			System.out.println("KNUTH MORRIS\nEl número de veces que el patrón se ha encontrado es: "+numeroIncidenciasKnuthMorris);
			numeroIncidenciasKnuthMorris= (numeroIncidenciasKnuthMorris*lineas);
			System.out.println("Por tanto, la estimación de veces que aparece el patrón es: " + numeroIncidenciasKnuthMorris);
			
			System.out.println("\nTiempo de ejecución: "+ (totalKM)+" milisegundos");
			
			System.out.println("\nBOYER MOORE\nEl número de veces que el patrón se ha encontrado es: "+numeroIncidenciasBoyer);
			numeroIncidenciasBoyer= (numeroIncidenciasBoyer*lineas);
			System.out.println("Por tanto, la estimación de veces que aparece el patrón es: " + numeroIncidenciasBoyer);
			
			System.out.println("\nTiempo de ejecución: "+ (totalBM)+" milisegundos");
			fr.close();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	
}