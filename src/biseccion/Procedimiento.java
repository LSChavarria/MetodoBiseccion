package biseccion;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Procedimiento {
    Pantalla pantalla = new Pantalla();
    
    public Procedimiento() {}
    
    public static double f(String s, double x) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put("x", x); 
        engine.put("e", 2.71828182845904); 
        Object operation;
        String resultado = null;
        try {
            s = s.replace("pow", "Math.pow");
            operation = engine.eval(s);
            resultado = operation.toString();
        }
        catch (ScriptException ev) {
            //resultado = "ERROR!";
        }
        return Double.parseDouble(resultado);
    }
    
    public static String procedimiento(double x1, double xu, double errorDeseado, String s) {
        double xr, aux = 0;
        double errorActual;
        
        if(f(s, x1) * f(s, xu) > 0) return "Error! No se cumple el requisito f(x1) * f(xu) <= 0";
        
        if(f(s, x1) == 0) return Double.toString(x1);
        if(f(s, xu) == 0) return Double.toString(xu);
        
        do{
            xr = (x1 + xu) / 2;
            if(f(s, x1) * f(s, xr) < 0) xu = xr;
            if(f(s, x1) * f(s, xr) > 0) x1 = xr;
            if(f(s, x1) * f(s, xr) == 0) break;

            errorActual = ((xr - aux) / xr) * 100;
            if(errorActual < 0) errorActual *= -1;
            
            aux = xr;
        }while(errorActual > errorDeseado);
        
        return Double.toString(xr);
    }
    
}

//pow(x,3)-0.5*pow(x,2)-8*x+7.5 0 2.499999999 0.00000000000001
//pow(e,-x)-x 0 1 5
//-0.874*pow(x,2)+1.75*x+2.627 2.9 3.1 0.001
//(1-0.6*x)/x 1.5 2 0.02
//pow(x,3)-5*pow(x,2)+7*x-3 -1 3 5
//pow(x,3)-11*pow(x,2)+32*x-22 3.3 999 0.000000000000000000000000000000000000000000000001