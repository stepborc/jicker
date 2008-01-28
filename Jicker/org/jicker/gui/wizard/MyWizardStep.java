package org.jicker.gui.wizard;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.pietschy.wizard.InvalidStateException;
import org.pietschy.wizard.Wizard;
import org.pietschy.wizard.WizardModel;
import org.pietschy.wizard.models.StaticModel;

public class MyWizardStep extends Wizard
{
   private StaticModel model;
   private JPanel mainView;
   private JCheckBox agreeCheckbox;
   private JTextArea license;

   public MyWizardStep()
   {
     // super("My First Step", "A summary of the first step");

      // build and layout the components..
      mainView = new JPanel();
      agreeCheckbox = new JCheckBox("Agree");
      license = new JTextArea();
      mainView.setLayout(new FlowLayout());
      mainView.add(agreeCheckbox);
      //...

      // listen to changes in the state..
      agreeCheckbox.addItemListener(new ItemListener()
      {
         public void itemSelected(ItemEvent e)
         {
            // only continue if they agree
            MyWizardStep.this.setComplete(agreeCheckbox.isSelected());
         }

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
		}
      });
   }

   public void init(WizardModel model)
   {
      this.model = (StaticModel) model;
   }

   public void prepare()
   {
      // load our view...
      setView(mainView);
   }

   public void applyState()
   throws InvalidStateException
   {
      // display a progress bar of some kind..
      setView(myProgressView);

      setBusy(true);
      try
      {
         // do some work on another thread.. see Foxtrot
         ...
      }
      finally
      {
         setBusy(false);
      }

      // if error then throw an exception
      if (!ok)
      {
         // restore our original view..
         setView(mainView)
         // The wizard will display this message to the user.
         // You can prevent this by calling invalidStateException.setShowUser(false)
         throw new InvalidStateException("That didn't work!");
      }

      // all is well so update the model
      model.setAcceptsLicense(agreeCheckbox.isSelected());
   }

   public void getPreferredSize()
   {
      // use the size of our main view...
      mainView.getPreferredSize();
   }
}