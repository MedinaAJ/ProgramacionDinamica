package practicatextos;

import java.util.ArrayList;


public class AnalizaTextos {

	public static ArrayList<Integer> KnuthMorrisPrat(String patron,String texto){
			ArrayList<Integer> ocurrencias=new ArrayList<Integer>();
			if(patron.length()>0 && texto.length()>=patron.length()){
			int[] fallo=new int[patron.length()];
			fallo=preproceso(patron);//matriz de fallos
			ocurrencias=KnuthMorrisPrat(patron,texto,fallo);
		}
		return ocurrencias;
	}
	/**Algoritmo KnuthMorrisPrat*/
	public static int[] preproceso(String patron){
		int[] fallo=new int[patron.length()];
		int i=2;
		int j=0;
		fallo[0] = -1;
		if(patron.length()>1){
			fallo[1]=0;
			while (i < patron.length()) {
				if(patron.charAt(i-1)==patron.charAt(j)){
						j++;
						fallo[i]=j;
						i++;
				}
				else{
					if(j>0) j=fallo[j];
					else{
						fallo[i]=0;
						i++;
					}
				}
			}
		}
		return fallo;
	}
	public static ArrayList<Integer> KnuthMorrisPrat(String patron,String texto,int[] fallo){
		ArrayList<Integer> pos=new ArrayList<Integer>();
		int t=0;
		int p=0;
		while(texto.length()-t>=patron.length()){
			if(p==patron.length()){
					pos.add(t);
					p=0;
					t++;
			}
			else{
					if(texto.charAt(t+p)==patron.charAt(p)) p++;
					else{
						t=t+p-fallo[p];
						if(p>0) p=fallo[p];
					}
			}
		}
		return pos;
	}

	/**Algoritmo boyerMoore**/
	
	public static ArrayList<Integer> BoyerMoore(String patron,String texto){
		ArrayList<Integer> ocurrencias=new ArrayList<Integer>();
		if(patron.length()>0 && texto.length()>=patron.length()){
			ArrayList<Character> occChar=new ArrayList<Character>();
			ArrayList<Integer> occPos=new ArrayList<Integer>();
			iniciaOcc(occChar,occPos,patron);////Bad character
			//good-suffix heuristics
			int[] s=new int[patron.length()+1];
			int[] f=new int[patron.length()+1];
			preproceso1(s,f,patron);
			preproceso2(s,f,patron);
			boyerMoore(patron,texto,s,ocurrencias,occChar,occPos);
		}
		return ocurrencias;
	}
	
	private static void boyerMoore(String patron,String texto,int[] s,ArrayList<Integer>
		ocurrencias,ArrayList<Character> occChar,ArrayList<Integer> occPos){
		int i=0, j;
		while (i<=texto.length()-patron.length()) {
			j=patron.length()-1;
			while (j>=0 && patron.charAt(j)==texto.charAt(i+j)) j--;
			if (j<0) {
				ocurrencias.add(i);
				i+=s[0];
			}
			else{
				int aux=-1;
				if(occChar.contains(texto.charAt(i+j)))
				aux=occPos.get(occChar.indexOf(texto.charAt(i+j)));
				i+=Math.max(s[j+1], j-aux);
			}
		}
	}

	private static void iniciaOcc(ArrayList<Character> occChar,ArrayList<Integer>occPos,String patron){
			//Bad character
			//hace la funcion occ occ("patrona",a)=6 occ("patrona",n)=5 la ‘a’ de pos 1 no se cuenta
			//solo la mas lejana
		for(int n=patron.length()-1;n>=0;n--)
			if(!occChar.contains(patron.charAt(n))){
				occChar.add(patron.charAt(n));
				occPos.add(n);
			}
		}
	
	private static void preproceso1(int[] s,int [] f,String patron){
		//El sufijo coincidente se produce en algún lugar dentro del patrón
		int i=patron.length(), j=i+1;
		f[i]=j;
		while (i>0){
			while (j<=patron.length() && patron.charAt(i-1)!=patron.charAt(j-1)){
				if (s[j]==0) s[j]=j-i;
				j=f[j];
			}
			i--; j--;
			f[i]=j;
		}
	}

	private static void preproceso2(int[] s,int [] f,String patron){
		int i, j;
		j=f[0];
		for (i=0; i<=patron.length(); i++) {
			if (s[i]==0) s[i]=j;
			if (i==j) j=f[j];
		}
	}

}