package world;

import entity.EntityPresets;

public class Structures {
	
	public static void buildHouseStandardExterior(float x, float y, float z, float xWidth, float height, float zWidth) {
		for(int j = 0; j < height; j++) {
			for(int k = 0; k <= zWidth; k++) {
				EntityPresets.newBlockWood(x, y + j, z + k);
				EntityPresets.newBlockWood(x + xWidth, y + j, z + k);
			}
			
			for(int i = 0; i <= xWidth; i++) {
				EntityPresets.newBlockWood(x + i, y + j, z);
				if(i < 5 || i > 6 || j > 1) {
					EntityPresets.newBlockWood(x + i, y + j, z + zWidth);
				}
			}
		}
		
		for(int i = 1; i < xWidth; i++) {
			for(int k = 1; k < zWidth; k++) {
				EntityPresets.newBlockWood(x + i, y + height, z + k);
				
				EntityPresets.newSlopeWood(x, y + height, z + k).setRotation(-90);
				EntityPresets.newSlopeWood(x + xWidth, y + height, z + k).setRotation(90);
				EntityPresets.newSlopeWood(x + i, y + height, z).setRotation(180);
				EntityPresets.newSlopeWood(x + i, y + height, z + zWidth);
			}
		}
	}
	
	public static void buildHouseStandardInterior(float xWidth, float zWidth) {
		for(int i = 0; i < xWidth; i++) {
			for(int k = 0; k < zWidth; k++) {
				EntityPresets.newBlockWood(i, -1, k);
			}
			
			EntityPresets.newBlockWood(i, 0, 0);
			EntityPresets.newBlockWood(i, 0, zWidth);
		}
		
		for(int k = 0; k < zWidth; k++) {
			EntityPresets.newBlockWood(0, 0, k);
			EntityPresets.newBlockWood(xWidth, 0, k);
		}
	}
}