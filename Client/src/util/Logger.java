package util;

public class Logger {
    private static Logger instance;
    private static Object lock = new Object();
    private Logger(){  }

    public static Logger getInstance(){
        if( instance == null){
            synchronized (lock){
                if( instance == null)
                    instance = new Logger();
            }
        }
        return instance;
    }

    private void print(Object o){
        System.out.println("LOGGER: "+ o);
    }

    public static void log(Object o){
        getInstance().print(o);
    }
}
