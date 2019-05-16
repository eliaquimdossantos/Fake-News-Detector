package br.com;

public class testes {
	public static void main(String[] args) {
		String text = "	Lorem ipsum donec proin curabitur consequat lectus ullamcorper, tristique nullam fringilla curabitur sollicitudin lectus, nec pulvinar platea sit volutpat metus. quis gravida eleifend pellentesque aliquam donec accumsan potenti, ornare congue aenean ut ac inceptos lorem massa, phasellus ut vel neque primis mi. rutrum orci mauris varius rutrum libero molestie luctus et donec, urna etiam ipsum sociosqu gravida tortor semper dapibus ad, nunc quisque potenti sapien facilisis dui mi quis. ornare commodo curabitur accumsan platea fringilla euismod eget diam, auctor vestibulum neque class sapien augue dui litora ultrices, cubilia magna mi consequat blandit hendrerit sed. \n" + 
				"\n" + 
				"	Fames ut sapien sagittis sollicitudin eu neque ut dui venenatis, condimentum senectus ac porta faucibus gravida tortor class quisque vel, justo tempus velit orci fusce ut dolor molestie. porta donec in pretium eros habitasse mi mollis conubia gravida neque, mi vitae bibendum urna condimentum nec lorem turpis sodales, consequat platea augue tortor mauris adipiscing placerat odio est. egestas sociosqu faucibus odio quisque diam dolor egestas interdum curae, ultrices consectetur aliquet id posuere porttitor quis scelerisque, eros eget a vivamus ante mattis ad nulla. lacinia curabitur accumsan non adipiscing hendrerit posuere id libero, sed taciti porta pretium euismod lorem donec, habitasse eros venenatis aenean neque ultrices malesuada. ";
		
		Formater f = new Formater(text);
		f.removeIfSizeLessThan(3);
		
	}
}
