
package noughtsandcrosses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author AniaGadzetB
 */
public class NoughtsAndCrosses {
    
    static JFrame frame;
    static JPanel content;
    static JPanel line1;
    static JPanel line2;
    static JPanel line3;
    static JButton button0;
    static JButton button1;
    static JButton button2;
    static JButton button3;
    static JButton button4;
    static JButton button5;
    static JButton button6;
    static JButton button7;
    static JButton button8;
    
    
    static GameBrain genius;
    
    
    
    public void drawGameBoard(){
        frame = new JFrame();
        content = new JPanel();
        line1 = new JPanel();
        line2 = new JPanel();
        line3 = new JPanel();
        
        button0= new JButton("_");
        button1= new JButton("_");
        button2= new JButton("_");
        button3= new JButton("_");
        button4= new JButton("_");
        button5= new JButton("_");
        button6= new JButton("_");
        button7= new JButton("_");
        button8= new JButton("_");

        
        frame.setSize(200, 200);
        frame.getContentPane().add(content);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        
        content.add(line1);
        content.add(line2);
        content.add(line3);

        line1.add(button0);
        line1.add(button1);
        line1.add(button2);
        line2.add(button3);
        line2.add(button4);
        line2.add(button5);
        line3.add(button6);
        line3.add(button7);
        line3.add(button8);
        
        frame.setSize(200,200);
        frame.setVisible(true);
    }
    public void prepareGameBrain(){
        genius = new GameBrain();
    }
    public void activateGameBoard(){
        
        button0.addActionListener(new XListener0());
        button1.addActionListener(new XListener1());
        button2.addActionListener(new XListener2());
        button3.addActionListener(new XListener3());
        button4.addActionListener(new XListener4());
        button5.addActionListener(new XListener5());
        button6.addActionListener(new XListener6());
        button7.addActionListener(new XListener7());
        button8.addActionListener(new XListener8());
        
        
    }
    
    /**
     *
     * @param i
     */
    public void ComputerClicks(int i){
        switch(i){
            case 0:
                button0.setText("O");
                button0.setEnabled(false);
                break;
            case 1:
                button1.setText("O");
                button1.setEnabled(false);    
                break;
            case 2:
                button2.setText("O");
                button2.setEnabled(false);
                break;
            case 3:
                button3.setText("O");
                button3.setEnabled(false);
                break;
            case 4:
                button4.setText("O");
                button4.setEnabled(false);
                break;
            case 5:
                button5.setText("O");
                button5.setEnabled(false);    
                break;
            case 6:
                button6.setText("O");
                button6.setEnabled(false);
                break;
            case 7:
                button7.setText("O");
                button7.setEnabled(false);    
                break;
            default:
                button8.setText("O");
                button8.setEnabled(false);
                break;
        } 
    
    }
    
    public void putO(){
        int sp = genius.searchSpaceForO();
        genius.helpArray[sp]=-1;
        genius.oArray[genius.turn-1]=sp;
        ComputerClicks(sp);
    }
    
    public static void main(String[] args) {
        NoughtsAndCrosses game = new NoughtsAndCrosses();
        game.drawGameBoard();
        game.prepareGameBrain();
        game.activateGameBoard();
    }
    
    
    class GameBrain{
        int[] helpArray = new int[]{0,0,0,0,0,0,0,0,0};
        int[] xArray=new int[]{-1,-1,-1,-1,-1};
        int[] oArray=new int[]{-1,-1,-1,-1};
    
        int turn=0;

        public int searchSpaceForO(){
            switch(turn){
                    case 1:
                        if (helpArray[4]==1){
                            return 2;
                            
                        }else{
                            return 4;
                           
                        }
                        
                        
                        
                    case 2:
                        if(twoXInLine()){
                            return blockX();
                            

                        }else if(oArray[0]==4){
                            if(xArray[0]%2==1 && xArray[1]%2==1 && xArray[0]+xArray[1]!=8){
                                return xArray[0]+xArray[1]-4;
                            }else if(xArray[0]%2==0 && xArray[1]%2==0 && xArray[0]+xArray[1]==8){
                                return 2*((int) (Math.random()*4))+1;
                            }else{
                                return 12-(xArray[0]+xArray[1]);
                            }
                        }else if(xArray[0]==4 && xArray[1]%2==0 && oArray[0]+xArray[1]==8){
                            if(xArray[1]!=0){
                                return 0;
                            }else{return 2;}
                        }
                        
                    default:
                        if(twoOInLine()){
                            return putOInLine();
                        }else if(twoXInLine()){
                            return blockX();
                        }else if(oneOTwoNulls()){
                            return putOInLine2();    
                        }else{
                           for(int i=0;i<8;i++){
                               if(helpArray[i]==0){
                               return i;}
                           }
                            return 8;
                        }
                         
                       
            }
        }
        
 
        
        public boolean twoXInLine(){
            for(int i=0;i<7;i=i+3){
                if(helpArray[i]+helpArray[i+1]+helpArray[i+2]==2){
                    return true;
                }
            }
            for(int i=0;i<3;i++){
                if(helpArray[i]+helpArray[i+3]+helpArray[i+6]==2){
                    return true;
                }
            }
            return helpArray[0]+helpArray[4]+helpArray[8]==2 || 
                   helpArray[2]+helpArray[4]+helpArray[6]==2;
            
                    
        }
        public int blockX(){
            if(twoXInLine()){
            for(int i=0;i<7;i=i+3){
                if(helpArray[i]+helpArray[i+1]+helpArray[i+2]==2){
                    
                    for(int j=i; j<i+3;j++){
                        if (helpArray[j]==0){return j;}
                    }
                    
                    
                }
            }
            for(int i=0;i<3;i++){
                if(helpArray[i]+helpArray[i+3]+helpArray[i+6]==2){
                    for(int j=i; j<i+7;j=j+3){
                        if (helpArray[j]==0){return j;}
                    }
                }
            }
             if (helpArray[0]+helpArray[4]==2 && helpArray[8]==0){
                 return 8;
             }
             if (helpArray[0]+helpArray[8]==2 && helpArray[4]==0){
                 return 4;
             }
             if (helpArray[8]+helpArray[4]==2 && helpArray[0]==0){
                 return 0;
             }
             
             if(helpArray[2]+helpArray[4]==2 && helpArray[6]==0){
                 return 6;
             }
             
             if(helpArray[2]+helpArray[6]==2 && helpArray[4]==0){
                 return 4;
             }
             
             if(helpArray[6]+helpArray[4]==2 && helpArray[2]==0){
                 return 2;
             }
            }
            
            return 7;
            
            
           
            
        }
        public boolean twoOInLine(){
            for(int i=0;i<7;i=i+3){
                if(helpArray[i]+helpArray[i+1]+helpArray[i+2]==-2){
                    return true;
                }
            }
            for(int i=0;i<3;i++){
                if(helpArray[i]+helpArray[i+3]+helpArray[i+6]==-2){
                    return true;
                }
            }
            return helpArray[0]+helpArray[4]+helpArray[8]==-2 || 
                   helpArray[2]+helpArray[4]+helpArray[6]==-2;
            
        }
        public int putOInLine(){
            for(int i=0;i<7;i=i+3){
                if(helpArray[i]+helpArray[i+1]+helpArray[i+2]==-2){
                    
                    for(int j=i; j<i+3;j++){
                        if (helpArray[j]==0){return j;}
                    }
                    
                    
                }
            }
            for(int i=0;i<3;i++){
                if(helpArray[i]+helpArray[i+3]+helpArray[i+6]==-2){
                    for(int j=i; j<i+7;j=j+3){
                        if (helpArray[j]==0){return j;}
                    }
                }
            }
             if (helpArray[0]+helpArray[4]==-2 && helpArray[8]==0){
                 return 8;
             }
             if (helpArray[0]+helpArray[8]==-2 && helpArray[4]==0){
                 return 4;
             }
             if (helpArray[8]+helpArray[4]==-2 && helpArray[0]==0){
                 return 0;
             }
             
             if(helpArray[2]+helpArray[4]==-2 && helpArray[6]==0){
                 return 6;
             }
             
             if(helpArray[2]+helpArray[6]==-2 && helpArray[4]==0){
                 return 4;
             }
             
             if(helpArray[6]+helpArray[4]==-2 && helpArray[2]==0){
                 return 2;
             }
            
            
         return 0;   
            
        }
        public boolean oneOTwoNulls(){
            for(int i=0;i<7;i=i+3){
                if(helpArray[i]+helpArray[i+1]+helpArray[i+2]==-1 && helpArray[i]*helpArray[i+1]*helpArray[i+2]==0){
                    return true;
                }
            }
            for(int i=0;i<3;i++){
                if(helpArray[i]+helpArray[i+3]+helpArray[i+6]==-1 && helpArray[i]*helpArray[i+3]*helpArray[i+6]==0){
                    return true;
                }
            }
            return (helpArray[0]+helpArray[4]+helpArray[8]==-1 && helpArray[0]*helpArray[4]*helpArray[8]==0 )|| 
                   (helpArray[2]+helpArray[4]+helpArray[6]==-1 && helpArray[2]*helpArray[4]*helpArray[6]==0);
            
        }
       
        public int putOInLine2(){
            for(int i=0;i<7;i=i+3){
                if(helpArray[i]+helpArray[i+1]+helpArray[i+2]==-1 && helpArray[i]*helpArray[i+1]*helpArray[i+2]==0){
                    
                    for(int j=i; j<i+3;j++){
                        if (helpArray[j]==0){return j;}
                    }
                    
                    
                }
            }
            for(int i=0;i<3;i++){
                if(helpArray[i]+helpArray[i+3]+helpArray[i+6]==-1 && helpArray[i]*helpArray[i+3]*helpArray[i+6]==0){
                    for(int j=i; j<i+7;j=j+3){
                        if (helpArray[j]==0){return j;}
                    }
                }
            }
             if (helpArray[0]+helpArray[4]==-1 && helpArray[8]==0){
                 return 8;
             }
             if (helpArray[0]+helpArray[8]==-1 && helpArray[4]==0){
                 return 4;
             }
             if (helpArray[8]+helpArray[4]==-1 && helpArray[0]==0){
                 return 0;
             }
             
             if(helpArray[2]+helpArray[4]==-1 && helpArray[6]==0){
                 return 6;
             }
             
             if(helpArray[2]+helpArray[6]==-1 && helpArray[4]==0){
                 return 4;
             }
             
             if(helpArray[6]+helpArray[4]==-1 && helpArray[2]==0){
                 return 2;
             }
            
            
         return 0;   
            
        }
        
 
    }
    
    
    class XListener0 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                button0.setText("X");
                button0.setEnabled(false);
                genius.helpArray[0]=1;
                genius.xArray[genius.turn]=0;
                genius.turn++;
                putO();
                /*int sp = genius.searchSpaceForO();
                genius.helpArray[sp]=-1;
                genius.oArray[genius.turn-1]=sp;
                ComputerClicks(sp);
                */
             /*   KnotsAndCrosses.putXInTabH(0);
               int knot = KnotsAndCrosses.wherePutO();
                
                ComputerClicks(knot+1); */
        }        
    }
    
    class XListener1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                button1.setText("X");
                button1.setEnabled(false);
                genius.helpArray[1]=1;
                genius.xArray[genius.turn]=1;
                genius.turn++;
                if(genius.turn<5){
                    putO();
                }
                /*
                int sp = genius.searchSpaceForO();
                genius.helpArray[sp]=-1;
                genius.oArray[genius.turn-1]=sp;
                ComputerClicks(sp);
*/
            //    KnotsAndCrosses.putXInTabH(0);                    
        }        
    }
    
    class XListener2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                button2.setText("X");
                button2.setEnabled(false);
                genius.helpArray[2]=1;
                genius.turn++;
                genius.xArray[genius.turn-1]=2;
                if(genius.turn<5){
                    putO();
                }            /*
                int sp = genius.searchSpaceForO();
                genius.helpArray[sp]=-1;
                genius.oArray[genius.turn-1]=sp;
                ComputerClicks(sp); */
//                KnotsAndCrosses.putXInTabH(0);                    
        }        
    }
    
    class XListener3 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                button3.setText("X");
                button3.setEnabled(false);
                genius.helpArray[3]=1;
                genius.turn++;
                genius.xArray[genius.turn-1]=3;
                if(genius.turn<5){
                    putO();
                }            /*
                int sp = genius.searchSpaceForO();
                genius.helpArray[sp]=-1;
                genius.oArray[genius.turn-1]=sp;
                ComputerClicks(sp); */
             //   KnotsAndCrosses.putXInTabH(0);                    
        }        
    }
  
    class XListener4 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                button4.setText("X");
                button4.setEnabled(false);
                genius.helpArray[4]=1;
                genius.turn++;
                genius.xArray[genius.turn-1]=4;
                if(genius.turn<5){
                    putO();
                }/*
                int sp = genius.searchSpaceForO();
                genius.helpArray[sp]=-1;
                genius.oArray[genius.turn-1]=sp;
                ComputerClicks(sp); */
           //     KnotsAndCrosses.putXInTabH(0);                    
        }        
    }
    
    class XListener5 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                button5.setText("X");
                button5.setEnabled(false);
                genius.helpArray[5]=1;
                genius.turn++;
                genius.xArray[genius.turn-1]=5;
                if(genius.turn<5){
                    putO();
                }            /*
                int sp = genius.searchSpaceForO();
                genius.helpArray[sp]=-1;
                genius.oArray[genius.turn-1]=sp;
                ComputerClicks(sp); */
            //    KnotsAndCrosses.putXInTabH(0);                    
        }        
    }
    
    class XListener6 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                button6.setText("X");
                button6.setEnabled(false);
                genius.helpArray[6]=1;
                genius.turn++;
                genius.xArray[genius.turn-1]=6;
                if(genius.turn<5){
                    putO();
                }            /*
                int sp = genius.searchSpaceForO();
                genius.helpArray[sp]=-1;
                genius.oArray[genius.turn-1]=sp;
                ComputerClicks(sp); */
             //   KnotsAndCrosses.putXInTabH(0);                    
        }        
    }
    
    class XListener7 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                button7.setText("X");
                button7.setEnabled(false);
                genius.helpArray[7]=1;
                genius.turn++;
                genius.xArray[genius.turn-1]=7;
                if(genius.turn<5){
                    putO();
                }
                /*
                int sp = genius.searchSpaceForO();
                genius.helpArray[sp]=-1;
                genius.oArray[genius.turn-1]=sp;
                ComputerClicks(sp); */
//                KnotsAndCrosses.putXInTabH(0);                    
        }        
    }
    
    class XListener8 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                button8.setText("X");
                button8.setEnabled(false);
                genius.helpArray[8]=1;
                genius.turn++;
                genius.xArray[genius.turn-1]=8;
                if(genius.turn<5){
                    putO();
                }
                /*
                int sp = genius.searchSpaceForO();
                genius.helpArray[sp]=-1;
                genius.oArray[genius.turn-1]=sp;
                ComputerClicks(sp);
*/
             //   KnotsAndCrosses.putXInTabH(0);                    
        }        
    }
    
    
    
}
