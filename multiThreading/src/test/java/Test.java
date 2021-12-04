public class Test {
    private static volatile int count = 0;

    public static void main(String[] args){
        Thread[] threads = new Thread[5];
        for(int i = 0; i<5; i++){
            threads[i] = new Thread(()->{
                try{
                    for(int j = 0; j<10; j++){
                        System.out.println(++count);
                        Thread.sleep(500);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
    }
}
