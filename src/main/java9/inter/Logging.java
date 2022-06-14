package main.java9.inter;

public interface Logging {
    /**
     * 私有方法
     * @param message
     */
    private void log(String message) {
        System.out.println(message);
    }

    /**
     * 静态私有方法
     */
    private static void getConnection() {
        System.out.println("Open Database connection");
    }
}
