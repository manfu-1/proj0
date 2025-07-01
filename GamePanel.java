import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{

    public JFrame frame=null;
    public JPanel panel=null;
    private Card[][] cards=new Card[4][4];
    public String gameFlag="start";

    public GamePanel(JFrame f){
        this.setLayout(null);
        this.setOpaque(false);
        this.frame=f;
        this.panel=this;

        createMenu();
        initCard();

        createRandomNum();

        createKeyListener();
    }


    private void createKeyListener() {
        KeyAdapter ka=new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if (!"start".equals(gameFlag)){
                    return;
                }
                int key=e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP:
                        case KeyEvent.VK_W:
                            moveCard(1);
                            createRandomNum();
                        break;
                    case KeyEvent.VK_DOWN:
                        case KeyEvent.VK_S:
                            moveCard(3);
                            createRandomNum();
                        break;
                    case KeyEvent.VK_LEFT:
                        case KeyEvent.VK_A:
                            moveCard(4);
                            createRandomNum();
                        break;
                    case KeyEvent.VK_RIGHT:
                        case KeyEvent.VK_D:
                            moveCard(2);
                            createRandomNum();
                        break;
                    default:
                        break;
                }
            }
        };
        repaint();
        frame.addKeyListener(ka);
    }

    private void moveCard(int e){
        if (e==1){
            moveTop();
        }else if (e==2){
            moveRight();
        }else if (e==3){
            moveDown();
        }else if (e==4){
            moveLeft();
        }
    }

    private void moveLeft() {
      Card c;
        for (int i=0;i<4;i++){
            for (int j=1;j<4;j++){
                c=cards[i][j];

                if (c.num!=0){
                    c.moveCLeft(cards);
                }
            }
        }
        repaint();
    }


    private void moveDown() {
        Card c;
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                c=cards[i][j];

                if (c.num!=0){
                    c.moveCDown(cards);
                }
            }
        }
        repaint();
    }


    private void moveRight() {
       Card c;
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                c=cards[i][j];

                if (c.num!=0){
                    c.moveCRight(cards);
                }
            }
        }
        repaint();
    }


    private void moveTop() {
        Card c;
        for (int i=1;i<4;i++){
            for (int j=0;j<4;j++){
                c=cards[i][j];

                if (c.num!=0){
                    c.moveCTop(cards);
                }
            }
        }
        repaint();
    }


    private void createRandomNum(){
        int num=0;
        Random random=new Random();
        int index=random.nextInt(5)+1;
        if (index==1){
            num=4;
        }else{
            num=2;
        }

        if (isFull()){
            return;
        }

        Card c=getRandomCard(random);
        if (c!=null){
            c.setNum(num);
        }
    }

    private boolean isFull() {
       Card card;
       for (int i=0;i<4;i++){
        for (int j=0;j<4;j++){
            card=new Card(i, j);
            if (card.num==0){
            return false;
            }
        }
       }return true;
    }

    private Card getRandomCard(Random random) {
            int i = random.nextInt(4);
            int j = random.nextInt(4);
            Card c = cards[i][j];
            while (c.num != 0) {
        i = random.nextInt(4);
        j = random.nextInt(4);
        c = cards[i][j];
    }
    return c;
        }

    private void initCard() {
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                Card c=new Card(i, j);
                cards[i][j]=c;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                Card c=cards[i][j];
                c.draw(g);
            }
        }
    }

    private void createMenu(){

        Font tFont=createFont();
        //create jmenu bar
        JMenuBar jmb=new JMenuBar();
        JMenu jm1=new JMenu("Game");
        jm1.setFont(tFont);
        JMenuItem jmi1=new JMenuItem("new Game");jmi1.setFont(tFont);
        JMenuItem jmi2=new JMenuItem("exit");jmi2.setFont(tFont);
        jm1.add(jmi1);
        jm1.add(jmi2);

        JMenu jm2=new JMenu("Help");
        jm2.setFont(tFont);
        JMenuItem jmi3=new JMenuItem("Rule");jmi3.setFont(tFont);
        JMenuItem jmi4=new JMenuItem("Documentation");jmi4.setFont(tFont);
        jm2.add(jmi3);
        jm2.add(jmi4);

        jmb.add(jm1);
        jmb.add(jm2);
        
        frame.setJMenuBar(jmb);

        jmi1.addActionListener(this);
        jmi2.addActionListener(this);
        jmi3.addActionListener(this);
        jmi4.addActionListener(this);

        jmi1.setActionCommand("restart");
        jmi2.setActionCommand("exit");
        jmi3.setActionCommand("help");
        jmi4.setActionCommand("win");

    }
@Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "restart":
                System.out.println("new Game");
                restart();
  
            case "exit":
                System.out.println("exit");
                Object[] options={"yes","no"};
                int res=JOptionPane.showOptionDialog(this, ":( are you sure to leave?","",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                if (res==0){
                    System.exit(0);
                }
                break;
            case "help":
                System.out.println("help");
                Object[] option={"got it"};
                int result=JOptionPane.showOptionDialog(this, "merge when both numbers same","",JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);
                if (result==0){
                }
                break;
           
            case "win":
                System.out.println("win");
                Object[] option1={"got it"};
                int result1=JOptionPane.showOptionDialog(this, "win if you get 2048","",JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,null,option1,option1[0]);
                if (result1==0){
                }
                break;
                
        }
    }

    private void restart() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'restart'");
}

    private Font createFont(){
        return new Font("Cambria",Font.BOLD,18);
    }

    
}
