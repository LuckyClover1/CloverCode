import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class StudentTest {
    public static void main(String[] args) {
        Student sd = new Student("dfs");
        List<Student> fs = new ArrayList<>();
        fs.add(new Student("testName"));
        sd.setFriends(fs);
        Student ss = (Student)sd.clone();
        System.out.println(ss);
        System.out.println(sd);

       /* CountDownLatch latch = new CountDownLatch(10);

        IntStream.range(0,10).forEach(i->new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                latch.countDown();

                System.out.println(Thread.currentThread().getName() + "结束");

            }
        }).start());


        try {

            TimeUnit.SECONDS.sleep(5);
            System.out.println("进入等待");
            latch.await();
            System.out.println("程序正常结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


    }
}
