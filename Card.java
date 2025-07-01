import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Card {
    private int x=0;//coordinate
    private int y=0;
    private int w=80;//size
    private int h=80;
    private int i=0;//index
    private int j=0;

    private int offset=5;
    public int num=0;
    // private boolean merge=false;

    public Card(int i,int j){
        this.i=i;
        this.j=j;

        cal();
    }

    private void cal(){
        this.x=offset+j*w+(j+1)*5;
        this.y=offset+i*h+(i+1)*5;
    }
	public void draw(Graphics g) {
        Color color=getColor();
        Color oldColor=g.getColor();
        g.setColor(color);
		g.fillRoundRect(x, y, w, h, 16,16);
if (num != 0) {

        g.setColor(new Color(125, 78, 51));
        Font font = new Font("Cambria", Font.BOLD, 18);
        g.setFont(font);

        FontMetrics fm = g.getFontMetrics();
        String text = num + "";
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        int tx = x + (w - textWidth) / 2;
        int ty = y + (h - textHeight) / 2 + fm.getAscent();

        g.drawString(text, tx, ty);
    }
        g.setColor(oldColor);
    }

    private Color getColor(){
        Color c=null;

        switch (num) {
            case 2:
                c=new Color(238,244,234);
                break;
            case 4:
                c=new Color(222,236,200);
                break;
            case 8:
                c=new Color(174,213,130);
                break;
            case 16:
                c=new Color(142,201,75);
                break;
            case 32:
                c=new Color(111,148,48);
                break;
            case 64:
                c=new Color(76,174,124);
                break;
            case 128:
                c=new Color(60,180,144);
                break;
            case 256:
                c=new Color(45,130,120);
                break;
            case 512:
                c=new Color(9,97,26);
                break;
            case 1024:
                c=new Color(242,177,121);
                break;
            case 2048:
                c=new Color(223,185,0);
                break;
            default:
                c=new Color(92,151,117);
                break;
        }
        return c;
    }

    public void setNum(int num2) {
        this.num=num2;
    }

	public void moveCLeft(Card[][] cards) {
if (j == 0) return;

    Card prev = cards[i][j-1];
    if (prev.num == 0) {
        int tmp = this.num;
        this.num = prev.num;
        prev.num = tmp;
        prev.moveCLeft(cards);
    } else if (prev.num == this.num) {
        prev.num = this.num * 2;
        this.num = 0;
    }
}

public void moveCRight(Card[][] cards) {
    if (j == 3) return;

    Card prev = cards[i][j+1];
    if (prev.num == 0) {
        int tmp = this.num;
        this.num = prev.num;
        prev.num = tmp;
        prev.moveCRight(cards);
    } else if (prev.num == this.num) {
        prev.num = this.num * 2;
        this.num = 0;
    }
}

public void moveCDown(Card[][] cards) {
    if (i == 3) return;

    Card prev = cards[i + 1][j];
    if (prev.num == 0) {
        int tmp = this.num;
        this.num = prev.num;
        prev.num = tmp;
        prev.moveCDown(cards);
    } else if (prev.num == this.num) {
        prev.num = this.num * 2;
        this.num = 0;
    }
}

public void moveCTop(Card[][] cards) {
    if (i == 0) return;

    Card prev = cards[i - 1][j];
    if (prev.num == 0) {
        int tmp = this.num;
        this.num = prev.num;
        prev.num = tmp;
        prev.moveCTop(cards);
    } else if (prev.num == this.num) {
        prev.num = this.num * 2;
        this.num = 0;
    }
}
}
