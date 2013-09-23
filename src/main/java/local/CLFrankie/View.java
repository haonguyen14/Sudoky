package local.CLFrankie;

import java.awt.*;
import java.awt.event.*;

public class View extends Frame
{
 public static void main(String[] args)
 {
  View v = new View("Sudoky");
  v.setSize(450, 350);
  v.setLocation(600, 50);
  v.setVisible(true);
 }

 public TextField[][] textBoxes; 

 public View(String title)
 {
  super(title);
  setBackground(Color.white);
  
  GridBagLayout gridBag = new GridBagLayout();
  GridBagConstraints c = new GridBagConstraints();
  setLayout(gridBag);

  Panel pnTextBoxes = new Panel(new GridLayout(9, 9));
  textBoxes = new TextField[9][9];

  for(int i = 0; i < textBoxes.length; i++)
  {
   for(int j = 0; j < textBoxes[i].length; j++)
   {
    textBoxes[i][j] = new TextField(2);
    pnTextBoxes.add(textBoxes[i][j]);
   }
  }

  c.fill = GridBagConstraints.BOTH;
  
  c.gridwidth = GridBagConstraints.REMAINDER;
  gridBag.setConstraints(pnTextBoxes, c);
  add(pnTextBoxes);

  Panel pnButton = new Panel();
  Button btSolve = new Button("Solve");
  btSolve.addActionListener(new SolveListener());
  Button btClear = new Button("Clear");
  btClear.addActionListener(new ClearListener());
  pnButton.add(btSolve);
  pnButton.add(btClear);
 
  c.gridwidth = GridBagConstraints.REMAINDER;
  gridBag.setConstraints(pnTextBoxes, c); 
  add(pnButton);

  addWindowListener(new WindowListenerExit());
 }
 
 public class WindowListenerExit extends WindowAdapter
 {
 	public void windowClosing(WindowEvent evt)
	{
		System.exit(0);
	}
 }
 
 public class SolveListener implements ActionListener
 {
 	public void actionPerformed(ActionEvent evt)
 	{
		int[][] iBoard = new int[9][9];
  		for(int i = 0; i < textBoxes.length; i++)
  		{
   			for(int j = 0; j < textBoxes[i].length; j++)
   			{
     				try
     				{
					int num = Integer.parseInt(textBoxes[i][j].getText());
					if(num > 0)
       						iBoard[i][j] = num;
					else
						iBoard[i][j] = -1;
     				}
     				catch(NumberFormatException e)
     				{
       					iBoard[i][j] = -1;
     				}
   			}
		}
  
  		//------------------------- calculating --------------------------------
  		Board board = new Board(iBoard);
  		Sudoky.solve(board);
  		//----------------------------------------------------------------------
  
  		for(int i = 0; i < textBoxes.length; i++)
   			for(int j = 0; j < textBoxes[i].length; j++)
    				textBoxes[i][j].setText((board.mData[i][j]) + "");;
   
 	}
 }

 public class ClearListener implements ActionListener
 {
	public void actionPerformed(ActionEvent evt)
	{
  		for(int i = 0; i < textBoxes.length; i++)
   			for(int j = 0; j < textBoxes[i].length; j++)
				textBoxes[i][j].setText("");
	}
 }
}
