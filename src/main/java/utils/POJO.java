package utils;

public class POJO {
	 private String hotelId;      // String because of "1001a"
	    private int roomId;
	    private String roomType;
	    private String roomStatus;
	    private double roomPrice;

	    // No-arg constructor
	    public POJO() {}

	    // Getters and Setters
	    public String getHotelId() { return hotelId; }
	    public void setHotelId(String hotelId) { this.hotelId = hotelId; }

	    public int getRoomId() { return roomId; }
	    public void setRoomId(int roomId) { this.roomId = roomId; }

	    public String getRoomType() { return roomType; }
	    public void setRoomType(String roomType) { this.roomType = roomType; }

	    public String getRoomStatus() { return roomStatus; }
	    public void setRoomStatus(String roomStatus) { this.roomStatus = roomStatus; }

	    public double getRoomPrice() { return roomPrice; }
	    public void setRoomPrice(double roomPrice) { this.roomPrice = roomPrice; }
}

