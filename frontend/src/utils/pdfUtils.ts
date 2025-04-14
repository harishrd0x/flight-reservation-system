
import { toast } from "@/components/ui/use-toast";

interface BookingData {
  id: string;
  flightId: string;
  from: string;
  fromCode: string;
  to: string;
  toCode: string;
  departureDate: string;
  departureTime: string;
  arrivalDate: string;
  arrivalTime: string;
  price: number;
  userName?: string;
  status: string;
  bookingDate?: string;
}

export const generateBookingPDF = (booking: BookingData): void => {
  // In a real app, we would use a library like jspdf or pdfmake
  // For this demo, we'll create a simple implementation that
  // generates a "fake" PDF by opening a new window with the booking details
  
  const htmlContent = `
    <!DOCTYPE html>
    <html>
    <head>
      <title>Booking Confirmation - ${booking.id}</title>
      <style>
        body {
          font-family: Arial, sans-serif;
          line-height: 1.6;
          max-width: 800px;
          margin: 0 auto;
          padding: 20px;
        }
        .header {
          text-align: center;
          margin-bottom: 30px;
          border-bottom: 2px solid #333;
          padding-bottom: 10px;
        }
        .logo {
          font-size: 24px;
          font-weight: bold;
          color: #4338ca;
        }
        .booking-id {
          font-size: 18px;
          margin-top: 10px;
        }
        .section {
          margin-bottom: 20px;
        }
        .section-title {
          font-weight: bold;
          font-size: 18px;
          margin-bottom: 10px;
          border-bottom: 1px solid #ddd;
          padding-bottom: 5px;
        }
        .flight-info {
          display: flex;
          justify-content: space-between;
          margin-bottom: 15px;
        }
        .price {
          font-size: 18px;
          font-weight: bold;
          text-align: right;
          margin-top: 20px;
        }
        .footer {
          margin-top: 50px;
          text-align: center;
          font-size: 12px;
          color: #666;
        }
        @media print {
          .no-print {
            display: none;
          }
        }
      </style>
    </head>
    <body>
      <div class="header">
        <div class="logo">Cloud Jet Airways</div>
        <div class="booking-id">Booking ID: ${booking.id}</div>
        <div>Status: ${booking.status.toUpperCase()}</div>
      </div>
      
      <div class="section">
        <div class="section-title">Flight Information</div>
        <div>Flight Number: ${booking.flightId}</div>
        <div class="flight-info">
          <div>
            <div><strong>From:</strong> ${booking.from} (${booking.fromCode})</div>
            <div>Date: ${booking.departureDate}</div>
            <div>Time: ${booking.departureTime}</div>
          </div>
          <div>
            <div><strong>To:</strong> ${booking.to} (${booking.toCode})</div>
            <div>Date: ${booking.arrivalDate}</div>
            <div>Time: ${booking.arrivalTime}</div>
          </div>
        </div>
      </div>
      
      <div class="section">
        <div class="section-title">Passenger Information</div>
        <div>Name: ${booking.userName || 'Not provided'}</div>
      </div>
      
      <div class="section">
        <div class="section-title">Booking Details</div>
        <div>Booking Date: ${booking.bookingDate || 'Not provided'}</div>
        <div class="price">Total Price: ₹${(booking.price * 83).toFixed(2)}</div>
      </div>
      
      <div class="footer">
        This is an electronic ticket. Please present this document at the airport check-in counter.
        <div>Generated on ${new Date().toLocaleString()}</div>
      </div>
      
      <div class="no-print" style="margin-top: 30px; text-align: center;">
        <button onclick="window.print()">Print Ticket</button>
      </div>
    </body>
    </html>
  `;
  
  const newWindow = window.open('', '_blank');
  if (newWindow) {
    newWindow.document.write(htmlContent);
    newWindow.document.close();
    
    toast({
      title: "PDF Generated",
      description: "Booking details have been prepared for printing.",
    });
  } else {
    toast({
      title: "Error",
      description: "Unable to open a new window. Please check your popup blocker settings.",
      variant: "destructive",
    });
  }
};
