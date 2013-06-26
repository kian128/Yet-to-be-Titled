package gui;

public class GuiComponent {
	
	protected float x, y, width, height;
	protected float red, green, blue;
	
	protected boolean isActive;
	
	public GuiComponent(float x, float y, float width, float height, float red, float green, float blue) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	protected void renderShapes(long delta) {
	}
	
	protected void renderText(long delta) {
	}
	
	protected void activateButton() {
	}
	
	protected void activateSliderUp() {
	}
	
	protected void activateSliderDown() {
	}
	
	
	public void appendString(String s) {
	}
	
	public void shortenString(int n) {
	}
	
	public void clearString() {
	}
	
	public String getText() {
		return null;
	}
}