import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class Front {
	
	private static List<Component> compList = new ArrayList<Component>();
	private static Hashtable<String, Object> map = new Hashtable<String, Object>();
	
	public static void invoke(JFrame tela, Class<?> c, String method) throws Exception{
		compList = getAllComponents(tela);
		addMap(compList);
		Object o = c.newInstance();
		Method m = c.getDeclaredMethod(method);
		Session.parameters = map;
		m.invoke(o);
	}

	private static void addMap(List<Component> compList2) {
		for(Component c : compList2){
			if(c instanceof JTextField){
				JTextField tf = (JTextField)c;
				map.put(tf.getName(), tf.getText());
			}			
		}
		
	}

	private static List<Component> getAllComponents(Container c) {
		Component[] comps = c.getComponents();
	    List<Component> compList = new ArrayList<Component>();
	    for (Component comp : comps) {
	        compList.add(comp);
	        if (comp instanceof Container)
	            compList.addAll(getAllComponents((Container) comp));
	    }
	    return compList;
		
	}
		
}
