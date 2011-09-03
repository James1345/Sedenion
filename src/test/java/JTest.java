import sedenion.*;
import javax.swing.*;
import java.awt.*;

public class JTest{

	public static void main(String[] args){

		JFrame f = new JFrame();
		JPanel p = new JPanel(){

			public void paintComponent(Graphics g){
				
				Graphics2D g2 = (Graphics2D)g;
				
				for(double r = -2; r < 2; r += 0.1){
					for(double e = -2; e < 2; e += 0.1){	
						Dual z = new Dual(r,e);
						for(int i = 0; i < 50; i++){
							z = z.multiply(z);
						}
						if(z.real < 2) {
							System.out.println(""+r+", "+e);
							int x = (int)((r+2)*100);
							int y = (int)((e+2)*100);
							g2.drawLine(x,y,x,y);
						}
					}
		
				}


			}
			


		};
		
		p.setPreferredSize(new Dimension(400,400));
		f.setContentPane(p);
		f.pack();
		f.setVisible(true);	
	
	}


}
