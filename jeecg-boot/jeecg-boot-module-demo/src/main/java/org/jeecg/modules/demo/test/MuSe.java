package org.jeecg.modules.demo.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @Description: TODO
 * @author: lzj
 * @date: 2022年01月07日 15:56
 */
public class MuSe {
    public static void main(String[] args) {
        MuSe myself = new MuSe();
        int ping =0;
        int ying = 0;
        int shu = 0;
        int yuanjifen = 0;
        //设置庄家本金
        int jifen = 0;
        //设置游戏场数
        int NN = 100;
        for (int tt = 0;tt<NN;tt++){
        //扑克牌
        ArrayList<String> pukepai = new ArrayList<>();
        //花色
        String[] colors = {"1-", "2-", "3-", "4-"};
        //点数
        String[] numbers = {"1","2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};

        for (String col : colors) {
            for (String num : numbers) {
                pukepai.add(col + num);
            }
        }
            //设置人数
            ArrayList<ArrayList<String>> dugous = myself.setpeople(5);
            //洗牌
            Collections.shuffle(pukepai);
        //发牌
        int index = 0;
        for (int f = 0; f < 2; f++) {
            for (int p = 0; p < dugous.size(); p++) {
                dugous.get(p).add(pukepai.get(index));
                index++;
            }
        }
//        for(int k=0;k<dugous.size();k++){
//            System.out.println("未补牌时的手牌");
//            System.out.print("玩家"+k+"的牌有:");
//            for(int p=0;p<dugous.get(k).size();p++){
//                System.out.println(dugous.get(k).get(p)+" ");
//            }
//        }

        //判断补牌逻辑：1、携带1补；2、小于等于5补；3、6、7点的两张牌有机会补成4倍（顺子）、5倍（同数）、6倍（同花顺）补
        for (int b = 0; b < dugous.size(); b++) {
            if (dugous.get(b).get(0).substring(dugous.get(b).get(0).length() - 1, dugous.get(b).get(0).length()).equals("1") ||
                    dugous.get(b).get(1).substring(dugous.get(b).get(1).length() - 1, dugous.get(b).get(1).length()).equals("1")) {
                dugous.get(b).add(pukepai.get(index));
                index++;
                continue;
            } else if ((myself.huansuan(dugous.get(b).get(0).substring(2, dugous.get(b).get(0).length())) +
                    myself.huansuan(dugous.get(b).get(1).substring(2, dugous.get(b).get(1).length()))) % 10 <= 5) {
                dugous.get(b).add(pukepai.get(index));
                index++;
                continue;
            } else if (((myself.huansuan(dugous.get(b).get(0).substring(2, dugous.get(b).get(0).length())) +
                    myself.huansuan(dugous.get(b).get(1).substring(2, dugous.get(b).get(1).length()))) % 10 == 6 || (myself.huansuan(dugous.get(b).get(0).substring(2, dugous.get(b).get(0).length())) +
                    myself.huansuan(dugous.get(b).get(1).substring(2, dugous.get(b).get(1).length()))) % 10 == 7) && (Math.abs(Integer.parseInt(dugous.get(b).get(0).substring(2, dugous.get(b).get(0).length())) -
                    Integer.parseInt(dugous.get(b).get(1).substring(2, dugous.get(b).get(1).length()))) <= 2)) {
                dugous.get(b).add(pukepai.get(index));
                index++;
                continue;
            }
        }
        //显示手牌
//        for(int kk=0;kk<dugous.size();kk++){
//            System.out.print("玩家"+kk+"的牌有:");
//            for(int p=0;p<dugous.get(kk).size();p++){
//                System.out.println(dugous.get(kk).get(p)+" ");
//            }
//        }
        //赌博N论之后庄家的积分，原始积分为0

        for (int xx = 1; xx < dugous.size(); xx++) {
            int zheng = myself.pk(dugous.get(0), dugous.get(xx));
            if (zheng > 0) {
                jifen = jifen + zheng;
                if(xx == 4){
                    int ttt=tt+1;
                    if(jifen>yuanjifen){
                        ying++;
                    }
                    else if(jifen<yuanjifen){
                        shu++;
                    }else ping++;
                    yuanjifen = jifen;
                    System.out.println("第"+ttt+"把后你的余额为"+jifen);
                }
                continue;
            }
            ;
            int fu = myself.pk(dugous.get(xx), dugous.get(0));
            if (fu > 0) {
                jifen = jifen - fu;
                if(xx == 4){
                    int ttt=tt+1;
                    if(jifen>yuanjifen){
                        ying++;
                    }
                    else if(jifen<yuanjifen){
                        shu++;
                    }else ping++;
                    yuanjifen = jifen;
                    System.out.println("第"+ttt+"把后你的余额为"+jifen);
                }
                continue;
            }
            if(xx == 4){
                int ttt=tt+1;
                if(jifen>yuanjifen){
                    ying++;
                }
                else if(jifen<yuanjifen){
                    shu++;
                }else ping++;
                yuanjifen = jifen;
                System.out.println("第"+ttt+"把后你的余额为"+jifen);
            }
        }
    }
        System.out.println("在这"+NN+"把赌博中，你赢的次数是"+ying+"次，"+"输的次数是"+shu+"次，"+"平局的次数是"+ping+"次；");
        System.out.println("本金为0且作为庄家的你，打了"+NN+"把后，你的余额为："+jifen);
//
    }

    //设置玩家人数,初始化玩家手牌
    public ArrayList<ArrayList<String>> setpeople(int i) {
        ArrayList<ArrayList<String>> peoples = new ArrayList<>();
        for (int x = 0; x < i; x++) {
            ArrayList<String> player = new ArrayList<>();
            peoples.add(player);
        }
        return peoples;
    }

    //返回用于计算的值：11、12、13当10
    public int huansuan(String h) {
        switch (h) {
            case "11":
                h = "10";
                break;
            case "12":
                h = "10";
                break;
            case "13":
                h = "10";
                break;
        }
        return Integer.parseInt(h);
    }

    //比较两幅牌的大小,返回第一个玩家的得分情况
    public int pk(ArrayList<String> a, ArrayList<String> b) {
        //玩家1有序花色数组
        ArrayList<Integer> col1 = new ArrayList<>();
        //玩家1有序大小数组
        ArrayList<Integer> nums1 = new ArrayList<>();
        //玩家2有序花色数组
        ArrayList<Integer> col2 = new ArrayList<>();
        //玩家2有序大小数组
        ArrayList<Integer> nums2 = new ArrayList<>();
        for (int aa = 0; aa < a.size(); aa++) {
            col1.add(Integer.parseInt(a.get(aa).substring(0, 1)));
            nums1.add(Integer.parseInt(a.get(aa).substring(2, a.get(aa).length())));
        }
        for (int bb = 0; bb < b.size(); bb++) {
            col2.add(Integer.parseInt(b.get(bb).substring(0, 1)));
            nums2.add(Integer.parseInt(b.get(bb).substring(2, b.get(bb).length())));
        }
//        for(int k=0;k<col1.size();k++){
//            System.out.print("玩家a的牌的花色为:");
//            System.out.println(col1.get(k));
//            System.out.println("玩家a的牌的大小为");
//            System.out.println(nums1.get(k));
//        }
//        for(int k=0;k<col2.size();k++){
//            System.out.print("玩家b的牌的花色为:");
//            System.out.println(col2.get(k));
//            System.out.println("玩家b的牌的大小为");
//            System.out.println(nums2.get(k));
//        }
        if (fiveteen(col1, nums1) == true && fiveteen(col2, nums2) == false) {
            return 50;
        } else if (tiangong(col1, nums1) == true && fiveteen(col2, nums2) == false) {
            if (tiangong(col2, nums2) == false && two(col1, nums1) == true) {
                return 2;
            } else if (tiangong(col2, nums2) == false && two(col1, nums1) == false) {
                return 1;
            } else if(tiangong(col2, nums2) == true && two(col1, nums1) == true&&jisuan(col1,nums1)>jisuan(col2,nums2)){
                return 2;
            } else if(tiangong(col2, nums2) == true && two(col1, nums1) == false&&jisuan(col1,nums1)>jisuan(col2,nums2)){
                return 1;
            }
        } else if(eighth(col1,nums1)==true && fiveteen(col2, nums2) == false && tiangong(col2, nums2) == false && eighth(col2,nums2)==false){
                return 8;
        } else if(six(col1,nums1)==true && fiveteen(col2, nums2) == false && tiangong(col2, nums2) == false && eighth(col2,nums2)==false){
            if(six(col2,nums2)==true){
                Collections.sort(nums1);
                Collections.sort(nums2);
                if(nums1.contains(1)&&nums2.contains(1)==false){
                    if(nums1.get(2)>= nums2.get(2)){
                        return 6;
                    }

                }
                else if(nums1.contains(1)==false&&nums2.contains(1)==false){
                    if(nums1.get(2)> nums2.get(2)){
                        return 6;
                    }
                }
                else if(nums1.contains(1)==false&&nums2.contains(1)){
                    if(nums1.get(1)> nums2.get(2)){
                        return 6;
                    }
                }
                else if(nums1.contains(1)&&nums2.contains(1)){
                    if(nums1.get(2)> nums2.get(2)){
                        return 6;
                    }
                }
            }else return 6;

        }else if(four(col1,nums1)&&fiveteen(col2, nums2) == false && tiangong(col2, nums2) == false && eighth(col2,nums2)==false&&six(col2,nums2)==false){
            if(four(col2,nums2)==true){
                Collections.sort(nums1);
                Collections.sort(nums2);
                if(nums1.contains(1)&&nums2.contains(1)==false){
                    if(nums1.get(2)>= nums2.get(2)){
                        return 4;
                    }

                }
                else if(nums1.contains(1)==false&&nums2.contains(1)==false){
                    if(nums1.get(2)> nums2.get(2)){
                        return 4;
                    }
                }
                else if(nums1.contains(1)==false&&nums2.contains(1)){
                    if(nums1.get(1)> nums2.get(2)){
                        return 4;
                    }
                }
                else if(nums1.contains(1)&&nums2.contains(1)){
                    if(nums1.get(2)> nums2.get(2)){
                        return 4;
                    }
                }
            }else return 4;
        } else if(fiveteen(col2, nums2) == false && tiangong(col2, nums2) == false && eighth(col2,nums2)==false&&six(col2,nums2)==false&&four(col2,nums2)==false){
            if(jisuan(col1,nums1)>jisuan(col2,nums2)&&three(col1,nums1)){
                return 3;
            }
            else if(jisuan(col1,nums1)>jisuan(col2,nums2)&&two(col1,nums1)){
                return 2;
            }
            else if(jisuan(col1,nums1)>jisuan(col2,nums2)){return 1;}
        }
        return 0;
    }


    //判断是否为50倍
    public boolean fiveteen(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (b.size() == 3 && b.get(0) == 1 && b.get(1) == 1 && b.get(2) == 1) {
            return true;
        } else return false;
    }

    //判断是否为8倍
    public boolean eighth(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (b.size() == 3 && b.get(0) == 1 && b.get(1) == 1 ||b.size() == 3&& b.get(2) == 1 && b.get(1) == 1 || b.size() == 3 && b.get(0) == 1 && b.get(2) == 1) {
            return true;
        } else return false;
    }

    //判断是否为6倍数
    public boolean six(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.size() == 3 && a.get(0) == a.get(1) && a.get(1) == a.get(2)) {
            if (b.contains(1)) {
                b.remove(Integer.valueOf(1));
                if (Math.abs(b.get(0) - b.get(1)) > 0 && Math.abs(b.get(0) - b.get(1)) <= 2 || Math.abs(b.get(0) - b.get(1)) == 11&&Math.abs(b.get(0) - b.get(1)) > 0) {
                    b.add(1);
                    return true;
                }

            }
        } else if (a.size() == 3) {
            Collections.sort(b);
            if (Math.abs(b.get(0) - b.get(1)) == 1 && Math.abs(b.get(1) - b.get(2)) == 1) {
                return true;
            }
        }
        return false;
    }

    //判断是否为5倍数
    public boolean five(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.size() == 3 && b.contains(1)) {
            b.remove(Integer.valueOf(1));
            if (Math.abs(b.get(0) - b.get(1)) == 0) {
                b.add(1);
                return true;
            }
        } else if (a.size() == 3 && b.get(0) == b.get(1) && b.get(1) == b.get(2)) {
            return true;
        }

        return false;
    }

    //判断是否为4倍数
    public boolean four(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (b.size() == 3) {
            if (b.contains(1)) {
                b.remove(Integer.valueOf(1));
                if (Math.abs(b.get(0) - b.get(1)) > 0 && Math.abs(b.get(0) - b.get(1)) <= 2 || Math.abs(b.get(0) - b.get(1)) == 11&&Math.abs(b.get(0) - b.get(1)) > 0) {
                    b.add(1);
                    return true;
                }

            }
        } else if (b.size() == 3) {
            Collections.sort(b);
            if (Math.abs(b.get(0) - b.get(1)) == 1 && Math.abs(b.get(1) - b.get(2)) == 1) {
                return true;
            }
        }
        return false;
    }

    //判断是否为3倍数
    public boolean three(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.size() == 3 && a.get(0) == a.get(1) && a.get(1) == a.get(2)) {
            return true;
        } else return false;
    }

    //判断是否为2倍数
    public boolean two(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.size() == 2 && b.get(0) == b.get(1) || a.size() == 2 && a.get(0) == a.get(1)) {
            return true;
        } else return false;
    }

    //判断是否为天公
    public boolean tiangong(ArrayList<Integer> a, ArrayList<Integer> b) {
        MuSe muSe = new MuSe();
        if (b.size() == 2 && (muSe.huansuan(String.valueOf(b.get(0))) + muSe.huansuan(String.valueOf(b.get(1)))) % 10 == 8 ||
                b.size() == 2 && (muSe.huansuan(String.valueOf(b.get(0))) + muSe.huansuan(String.valueOf(b.get(1)))) % 10 == 9) {
            return true;
        } else return false;
    }

    //返回点数
    public int jisuan(ArrayList<Integer> a, ArrayList<Integer> b) {
        MuSe muSe = new MuSe();
        if (b.contains(1)) {
            return 9;
        } else {
            int sum = 0;
            for (int pp = 0; pp < b.size(); pp++) {
                sum = sum + muSe.huansuan(String.valueOf(b.get(pp)));
            }
            return sum % 10;
        }
    }


}
