/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerocombinatorio;

import java.util.Stack;

/**
 *
 * @author 9alej
 */
class CombinatorioPila {

    public CombinatorioPila(int n, int k) {
        double c;
        c = metodo(n,k);
        System.out.println(c);
    }

    private int metodo(int n, int k){
        int sol=0;
        
        Stack<Integer> pN = new Stack<Integer>();
        Stack<Integer> pK = new Stack<Integer>();
        Stack<Integer> pL = new Stack<Integer>();
        Stack<Integer> pS = new Stack<Integer>();
        
        pK.push(k);
        pN.push(n);
        pL.push(1);
        pS.push(0);  
       
        while(!pN.empty()){
            while(pK.peek()!=0 && pN.peek()!=pK.peek() && pN.peek()>pK.peek() && pL.peek()<=2){
                
                switch(pL.peek()){
                    case 1:
                        pN.push(pN.peek()-1);
                        pK.push(pK.peek()-1);
                        break;
                    case 2: 
                        pN.push(pN.peek()-1);
                        pK.push(pK.peek());
                        break;
                }
                pL.push(1);
                
                if(pK.peek()==0)
                    pS.push(1);
                else if(pK.peek()==pN.peek())
                    pS.push(1);
                else
                    pS.push(0);  
            }
            pK.pop();pN.pop();pL.pop();sol=pS.pop();
            if(!pN.empty()){
                pL.push(pL.pop()+1);
                pS.push(pS.pop()+sol);
            }
        }        
       
        
        return sol;
    }
}
