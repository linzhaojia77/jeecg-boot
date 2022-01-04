package org.jeecg;

import com.google.common.base.Stopwatch;
import kotlin.random.URandomKt;
import org.jeecg.common.util.PasswordUtil;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

//
//import org.jeecg.modules.demo.mock.MockController;
//import org.jeecg.modules.demo.test.entity.JeecgDemo;
//import org.jeecg.modules.demo.test.mapper.JeecgDemoMapper;
//import org.jeecg.modules.demo.test.service.IJeecgDemoService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = JeecgSystemApplication.class)
//public class SampleTest {
//
//	@Resource
//	private JeecgDemoMapper jeecgDemoMapper;
//	@Resource
//	private IJeecgDemoService jeecgDemoService;
////	@Resource
////	private ISysDataLogService sysDataLogService;
//	@Resource
//	private MockController mock;
//
//	@Test
//	public void testSelect() {
//		System.out.println(("----- selectAll method test ------"));
//		List<JeecgDemo> userList = jeecgDemoMapper.selectList(null);
//		Assert.assertEquals(5, userList.size());
//		userList.forEach(System.out::println);
//	}
//
//	@Test
//	public void testXmlSql() {
//		System.out.println(("----- selectAll method test ------"));
//		List<JeecgDemo> userList = jeecgDemoMapper.getDemoByName("Sandy12");
//		userList.forEach(System.out::println);
//	}
//
//	/**
//	 * 测试事务
//	 */
//	@Test
//	public void testTran() {
//		jeecgDemoService.testTran();
//	}
//
//	//author:lvdandan-----date：20190315---for:添加数据日志测试----
//	/**
//	 * 测试数据日志添加
//	 */
////	@Test
////	public void testDataLogSave() {
////		System.out.println(("----- datalog test ------"));
////		String tableName = "jeecg_demo";
////		String dataId = "4028ef81550c1a7901550c1cd6e70001";
////		String dataContent = mock.sysDataLogJson();
////		sysDataLogService.addDataLog(tableName, dataId, dataContent);
////	}
//	//author:lvdandan-----date：20190315---for:添加数据日志测试----
//}
public class SampleTest {
    public static void main(String[] args) {
        String userpassword = PasswordUtil.encrypt("jeecg", "123456", "mIgiYJow");
        System.out.println(userpassword);
    }
//     static class YThread implements Runnable {
//
//         private Object obj = new Object();
//         AtomicInteger t = new AtomicInteger(0);
//         @Override
//         public void run() {
//
//
//
//                 for (int i=0; i < 10; i++) {
//                     t.getAndIncrement();
//                 try {
//
//                         Thread.sleep(10);
//
//                     } catch (InterruptedException e) {
//
//                     }
//
//                     System.out.println(Thread.currentThread().getName() + " 在运行 " + t);
//
//                     if (Thread.currentThread().getName().equals("Thread1")) {
//
//                         Thread.yield();
//
//                     }
//
//
//             }
//         }
//         /**
//          * @param args
//
//          */
//     }
//     public static void main(String[] args) {
//
//// TODO Auto-generated method stub
//
//            YThread s = new YThread();
//
//            Thread t1 = new Thread(s, "Thread1");
//
//            Thread t2 = new Thread(s, "Thread2");
//
//            t1.start();
//
//            t2.start();
//
//    }

//    public static void main(String[] args) {
//    int N = 8; //工人数
//    Semaphore semaphore = new Semaphore(5); //机器数目
//        for(int i=0;i<N;i++)
//            new Worker(i,semaphore).start();
//}
//    static class Worker extends Thread{
//    private int num;
//    private Semaphore semaphore;
//    public Worker(int num,Semaphore semaphore){
//        this.num = num;
//        this.semaphore = semaphore;
//    }
//    @Override
//    public void run() {
//        try {
//            semaphore.acquire();
//            System.out.println("工人"+this.num+"占用一个机器在生产...");
//            Thread.sleep(5000);
//            System.out.println("工人"+this.num+"释放出机器");
//            semaphore.release();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } }
//    public static void main(String[] args) {
//    int N = 4;
//    CyclicBarrier barrier = new CyclicBarrier(4);
// for(int i=0;i<N;i++)
//            new Writer(barrier).start();
//}
//static class Writer extends Thread{
//    private CyclicBarrier cyclicBarrier;
//    public Writer(CyclicBarrier cyclicBarrier) {
//        this.cyclicBarrier = cyclicBarrier;
//    }
//    @Override
//    public void run() {
//        try {
//            Thread.sleep(5000); //以睡眠来模拟线程需要预定写入数据操作
//            System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕,等待其他线程写入完毕");
//                    cyclicBarrier.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }catch(BrokenBarrierException e){
//            e.printStackTrace();
//        }
//        System.out.println("所有线程写入完毕，继续处理其他任务，比如数据操作");
//    }
//}
//    public static void main(String[] args) {
//        int n=0;
//        for(int i=0;i<10000000;i++){
//            int x =0;
//            for (int j=0;j<100;j++){
//                    if(SampleTest.rank(j)>0.5){
//                        x++;
//                        if(x==10){
//                            n++;
//                            break;
//                        }
//                    }else {
//                        x=0;
//                    }
//            }
//        }
//        System.out.println(n);
//    }
//    public static double rank(int a){
//        return Math.random();
//    }
//}
}
