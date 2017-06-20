package PackSolver;




import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lucas
 */
public class Stack {
    
    private ArrayList<Propjogadas> pilha= new ArrayList<Propjogadas>();
  
 

    public boolean pilhaVazia() {
return this.pilha.isEmpty();
    }
            
    public void empilhar(Propjogadas mov) {
        
     this.pilha.add(mov);
    
    }
    
       public Propjogadas desempilhar() { // melhorar função pra retornar 
           Propjogadas resu = new Propjogadas();
           
           try{
           int i = this.pilha.size() -1;
            resu = this.pilha.remove(i);
            
           return resu;
           }
           catch(ArrayIndexOutOfBoundsException e){
           System.out.println("erooooooo"+e);
           return resu;
           }

    }
      public ArrayList<Propjogadas> returnList(){
      ArrayList<Propjogadas> pCopy = new ArrayList<Propjogadas>();
      pCopy.addAll(this.pilha);
      return pCopy;
      }

    public int tamanho() {
        return this.pilha.size();
    }

    public Propjogadas exibeUltimoValor() {
        return this.pilha.get(this.pilha.size() - 1);
    }

    

}