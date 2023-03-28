package main.conversores;

public final class EnumInigualesExcepcion extends RuntimeException{
   EnumInigualesExcepcion(String msg){
      super(msg);
   }
   EnumInigualesExcepcion(){
      super();
   }
}
