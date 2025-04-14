import { Link } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Plane, CreditCard, Calendar, MessageSquare, Shield, CheckCircle2, Users, Globe, Heart } from "lucide-react";
import Header from "@/components/layout/Header";

export default function LandingPage() {
  return (
    <div className="min-h-screen flex flex-col">
      <Header />
      
      {/* Hero Section with fixed background */}
      <section className="relative py-20 md:py-32">
        <div 
          className="absolute inset-0 bg-cover bg-center z-0" 
          style={{ 
            backgroundImage: 'url("https://images.unsplash.com/photo-1559367183-975d410de28e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80")',
            opacity: 0.3
          }}
        ></div>
        <div className="container px-4 md:px-6 relative z-10">
          <div className="grid gap-8 lg:grid-cols-2 lg:gap-12 items-center">
            <div className="space-y-6">
              <div className="inline-block bg-airline-blue/10 text-airline-blue px-3 py-1 rounded-full font-medium text-sm mb-2">
                #1 Airline in India
              </div>
              <h1 className="text-4xl font-extrabold tracking-tighter sm:text-5xl md:text-6xl">Book Your Dream Flights with Cloud Jet Services</h1>
              <p className="text-gray-600 md:text-xl max-w-lg">
                Your journey begins with us. Explore our wide range of flights and enjoy a seamless booking experience with the best airfares in India.
              </p>
              <div className="flex flex-col gap-3 min-[400px]:flex-row">
                <Link to="/login">
                  <Button className="bg-airline-blue hover:bg-airline-navy text-lg px-8 py-6">
                    Start Booking
                  </Button>
                </Link>
                <Link to="/register">
                  <Button variant="outline" className="text-lg px-8 py-6">Create an Account</Button>
                </Link>
              </div>
            </div>
            <div className="flex items-center justify-center relative">
              <img 
                src="https://images.unsplash.com/photo-1559367183-975d410de28e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80" 
                alt="Cloud Jet airplane" 
                className="rounded-2xl shadow-2xl w-full max-w-lg mx-auto object-cover animate-float relative z-10" 
              />
              <div className="absolute -bottom-6 -left-6 bg-white p-4 rounded-xl shadow-lg w-48 hidden md:block z-20">
                <div className="flex items-center">
                  <div className="bg-green-100 p-2 rounded-full">
                    <CheckCircle2 className="h-8 w-8 text-green-600" />
                  </div>
                  <div className="ml-3">
                    <p className="font-bold">Trusted</p>
                    <p className="text-sm text-gray-500">By 5+ Million Indians</p>
                  </div>
                </div>
              </div>
              <div className="absolute -top-6 -right-6 bg-white p-4 rounded-xl shadow-lg w-48 hidden md:block z-20">
                <div className="flex items-center">
                  <div className="bg-blue-100 p-2 rounded-full">
                    <Shield className="h-8 w-8 text-blue-600" />
                  </div>
                  <div className="ml-3">
                    <p className="font-bold">Secure</p>
                    <p className="text-sm text-gray-500">100% Safe Booking</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Stats Section */}
      <section className="py-12 bg-white border-y">
        <div className="container px-4 md:px-6">
          <div className="grid grid-cols-2 md:grid-cols-4 gap-4 md:gap-8">
            <div className="text-center p-4">
              <p className="text-3xl md:text-4xl font-bold text-airline-blue">5M+</p>
              <p className="text-gray-500">Happy Customers</p>
            </div>
            <div className="text-center p-4">
              <p className="text-3xl md:text-4xl font-bold text-airline-blue">100+</p>
              <p className="text-gray-500">Destinations</p>
            </div>
            <div className="text-center p-4">
              <p className="text-3xl md:text-4xl font-bold text-airline-blue">500+</p>
              <p className="text-gray-500">Daily Flights</p>
            </div>
            <div className="text-center p-4">
              <p className="text-3xl md:text-4xl font-bold text-airline-blue">99%</p>
              <p className="text-gray-500">On-time Performance</p>
            </div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-16 md:py-24 bg-white">
        <div className="container px-4 md:px-6">
          <div className="flex flex-col items-center justify-center space-y-4 text-center mb-12">
            <div className="bg-airline-blue/10 text-airline-blue px-3 py-1 rounded-full font-medium text-sm">
              Premium Services
            </div>
            <div className="space-y-2">
              <h2 className="text-3xl font-extrabold tracking-tighter sm:text-4xl md:text-5xl">Why Fly With Cloud Jet?</h2>
              <p className="text-gray-600 md:text-xl/relaxed lg:text-base/relaxed xl:text-xl/relaxed max-w-3xl mx-auto">
                Experience the premium services offered by Cloud Jet Services across India and internationally
              </p>
            </div>
          </div>
          <div className="mx-auto grid max-w-6xl grid-cols-1 gap-8 md:grid-cols-2 lg:grid-cols-4">
            <div className="flex flex-col items-center space-y-4 rounded-xl border border-gray-200 p-8 shadow-sm hover:shadow-md transition bg-white">
              <div className="rounded-full bg-airline-blue p-4">
                <Plane className="h-8 w-8 text-white" />
              </div>
              <h3 className="text-xl font-bold">Premium Flights</h3>
              <p className="text-center text-gray-600">Modern fleet with spacious seating and exceptional comfort on every journey</p>
            </div>
            <div className="flex flex-col items-center space-y-4 rounded-xl border border-gray-200 p-8 shadow-sm hover:shadow-md transition bg-white">
              <div className="rounded-full bg-airline-blue p-4">
                <CreditCard className="h-8 w-8 text-white" />
              </div>
              <h3 className="text-xl font-bold">Easy Payments</h3>
              <p className="text-center text-gray-600">Secure payment options including UPI, net banking, and all credit/debit cards</p>
            </div>
            <div className="flex flex-col items-center space-y-4 rounded-xl border border-gray-200 p-8 shadow-sm hover:shadow-md transition bg-white">
              <div className="rounded-full bg-airline-blue p-4">
                <Calendar className="h-8 w-8 text-white" />
              </div>
              <h3 className="text-xl font-bold">Flight Management</h3>
              <p className="text-center text-gray-600">Easily manage bookings, check-in online, and get real-time flight updates</p>
            </div>
            <div className="flex flex-col items-center space-y-4 rounded-xl border border-gray-200 p-8 shadow-sm hover:shadow-md transition bg-white">
              <div className="rounded-full bg-airline-blue p-4">
                <MessageSquare className="h-8 w-8 text-white" />
              </div>
              <h3 className="text-xl font-bold">24/7 Support</h3>
              <p className="text-center text-gray-600">Customer service available anytime via chat, call, or email in multiple languages</p>
            </div>
          </div>
        </div>
      </section>

      {/* Destinations Section */}
      <section className="py-16 md:py-24 bg-gray-50">
        <div className="container px-4 md:px-6">
          <div className="flex flex-col items-center justify-center space-y-4 text-center mb-12">
            <div className="bg-airline-blue/10 text-airline-blue px-3 py-1 rounded-full font-medium text-sm">
              Explore India & Beyond
            </div>
            <div className="space-y-2">
              <h2 className="text-3xl font-extrabold tracking-tighter sm:text-4xl">Popular Destinations</h2>
              <p className="text-gray-600 md:text-xl/relaxed lg:text-base/relaxed xl:text-xl/relaxed max-w-3xl mx-auto">
                Discover our most sought-after flight destinations across India and international locations
              </p>
            </div>
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
            <div className="relative group overflow-hidden rounded-2xl">
              <img 
                src="https://images.unsplash.com/photo-1587474260584-136574528ed5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2000&q=80" 
                alt="New Delhi" 
                className="w-full h-96 object-cover transition-transform duration-500 group-hover:scale-110"
              />
              <div className="absolute inset-0 bg-gradient-to-t from-black/80 to-transparent flex flex-col justify-end p-8">
                <div className="flex items-center">
                  <Globe className="h-5 w-5 text-white mr-2" />
                  <p className="text-white/90 text-sm">Delhi, India</p>
                </div>
                <h3 className="text-2xl font-bold text-white mt-2">New Delhi</h3>
                <p className="text-white/90 mb-3">India's historic capital</p>
                <p className="text-white font-bold">From ₹2,999</p>
                <Link to="/dashboard/flights" className="mt-4 text-white bg-airline-blue hover:bg-airline-navy px-5 py-2 rounded-md inline-block text-sm">
                  Explore Flights
                </Link>
              </div>
              <div className="absolute top-4 right-4 bg-white/90 px-3 py-1 rounded-full text-xs font-medium">
                Popular
              </div>
            </div>
            
            <div className="relative group overflow-hidden rounded-2xl">
              <img 
                src="https://images.unsplash.com/photo-1529253355930-ddbe423a2ac7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2000&q=80" 
                alt="Mumbai" 
                className="w-full h-96 object-cover transition-transform duration-500 group-hover:scale-110"
              />
              <div className="absolute inset-0 bg-gradient-to-t from-black/80 to-transparent flex flex-col justify-end p-8">
                <div className="flex items-center">
                  <Globe className="h-5 w-5 text-white mr-2" />
                  <p className="text-white/90 text-sm">Mumbai, India</p>
                </div>
                <h3 className="text-2xl font-bold text-white mt-2">Mumbai</h3>
                <p className="text-white/90 mb-3">The city of dreams</p>
                <p className="text-white font-bold">From ₹3,499</p>
                <Link to="/dashboard/flights" className="mt-4 text-white bg-airline-blue hover:bg-airline-navy px-5 py-2 rounded-md inline-block text-sm">
                  Explore Flights
                </Link>
              </div>
            </div>
            
            <div className="relative group overflow-hidden rounded-2xl">
              <img 
                src="https://images.unsplash.com/photo-1477587458883-47145ed94245?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2000&q=80" 
                alt="Dubai" 
                className="w-full h-96 object-cover transition-transform duration-500 group-hover:scale-110"
              />
              <div className="absolute inset-0 bg-gradient-to-t from-black/80 to-transparent flex flex-col justify-end p-8">
                <div className="flex items-center">
                  <Globe className="h-5 w-5 text-white mr-2" />
                  <p className="text-white/90 text-sm">UAE</p>
                </div>
                <h3 className="text-2xl font-bold text-white mt-2">Dubai</h3>
                <p className="text-white/90 mb-3">City of luxury</p>
                <p className="text-white font-bold">From ₹24,999</p>
                <Link to="/dashboard/flights" className="mt-4 text-white bg-airline-blue hover:bg-airline-navy px-5 py-2 rounded-md inline-block text-sm">
                  Explore Flights
                </Link>
              </div>
              <div className="absolute top-4 right-4 bg-white/90 px-3 py-1 rounded-full text-xs font-medium">
                International
              </div>
            </div>
          </div>

          <div className="flex justify-center mt-12">
            <Link to="/dashboard/flights">
              <Button className="bg-airline-blue hover:bg-airline-navy">View All Destinations</Button>
            </Link>
          </div>
        </div>
      </section>
      
      {/* Why Choose Us Section */}
      <section className="py-16 md:py-24 bg-white">
        <div className="container px-4 md:px-6">
          <div className="flex flex-col items-center justify-center space-y-4 text-center mb-12">
            <div className="bg-airline-blue/10 text-airline-blue px-3 py-1 rounded-full font-medium text-sm">
              Our Commitment
            </div>
            <div className="space-y-2">
              <h2 className="text-3xl font-extrabold tracking-tighter sm:text-4xl">Why Choose Us</h2>
              <p className="text-gray-600 md:text-xl/relaxed lg:text-base/relaxed xl:text-xl/relaxed max-w-3xl mx-auto">
                We're committed to providing the best travel experience for all our customers
              </p>
            </div>
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-2 gap-12">
            <div className="flex items-center justify-center">
              <img 
                src="https://images.unsplash.com/photo-1606768666853-403c90a981ad?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2000&q=80" 
                alt="Friendly cabin crew" 
                className="rounded-2xl shadow-xl max-h-96 object-cover"
              />
            </div>

            <div className="flex flex-col space-y-8 justify-center">
              <div className="flex items-start space-x-4">
                <div className="bg-airline-blue/10 p-4 rounded-xl">
                  <Shield className="h-8 w-8 text-airline-blue" />
                </div>
                <div>
                  <h3 className="text-xl font-bold">Safety First</h3>
                  <p className="text-gray-600 mt-2">Your safety is our utmost priority with rigorous maintenance protocols, regular safety audits, and highly trained staff.</p>
                </div>
              </div>
              
              <div className="flex items-start space-x-4">
                <div className="bg-airline-blue/10 p-4 rounded-xl">
                  <CheckCircle2 className="h-8 w-8 text-airline-blue" />
                </div>
                <div>
                  <h3 className="text-xl font-bold">Best Price Guarantee</h3>
                  <p className="text-gray-600 mt-2">We offer competitive pricing, regular promotions, and special discounts to ensure you get the best value for your money.</p>
                </div>
              </div>
              
              <div className="flex items-start space-x-4">
                <div className="bg-airline-blue/10 p-4 rounded-xl">
                  <Users className="h-8 w-8 text-airline-blue" />
                </div>
                <div>
                  <h3 className="text-xl font-bold">Exceptional Service</h3>
                  <p className="text-gray-600 mt-2">Our dedicated support team is available 24/7 to assist with any queries or concerns in multiple Indian languages.</p>
                </div>
              </div>

              <div className="flex items-start space-x-4">
                <div className="bg-airline-blue/10 p-4 rounded-xl">
                  <Heart className="h-8 w-8 text-airline-blue" />
                </div>
                <div>
                  <h3 className="text-xl font-bold">Customer Satisfaction</h3>
                  <p className="text-gray-600 mt-2">With a 96% customer satisfaction rate, we continuously strive to exceed expectations on every flight.</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Call to Action */}
      <section className="py-16 bg-airline-blue text-white">
        <div className="container px-4 md:px-6">
          <div className="flex flex-col items-center text-center space-y-6 max-w-3xl mx-auto">
            <h2 className="text-3xl font-bold tracking-tighter sm:text-4xl">Ready to Experience Cloud Jet Services?</h2>
            <p className="text-white/80 md:text-xl">Join millions of satisfied customers who trust us for their travel needs across India and beyond.</p>
            <div className="flex flex-col sm:flex-row gap-4">
              <Link to="/login">
                <Button className="bg-white text-airline-blue hover:bg-gray-100 text-lg px-8 py-6">
                  Sign In
                </Button>
              </Link>
              <Link to="/register">
                <Button variant="outline" className="border-white text-white hover:bg-white/20 text-lg px-8 py-6">Create Account</Button>
              </Link>
            </div>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-gray-900 text-white py-12 mt-auto">
        <div className="container px-4 md:px-6">
          <div className="grid grid-cols-2 md:grid-cols-4 gap-8">
            <div>
              <h3 className="font-bold mb-4">Company</h3>
              <ul className="space-y-3">
                <li><Link to="/about" className="text-gray-300 hover:text-white transition">About Us</Link></li>
                <li><a href="#" className="text-gray-300 hover:text-white transition">Careers</a></li>
                <li><Link to="/contact" className="text-gray-300 hover:text-white transition">Contact</Link></li>
                <li><a href="#" className="text-gray-300 hover:text-white transition">Press</a></li>
              </ul>
            </div>
            <div>
              <h3 className="font-bold mb-4">Services</h3>
              <ul className="space-y-3">
                <li><Link to="/dashboard/flights" className="text-gray-300 hover:text-white transition">Domestic Flights</Link></li>
                <li><Link to="/dashboard/flights" className="text-gray-300 hover:text-white transition">International Flights</Link></li>
                <li><a href="#" className="text-gray-300 hover:text-white transition">Special Offers</a></li>
                <li><a href="#" className="text-gray-300 hover:text-white transition">Gift Cards</a></li>
              </ul>
            </div>
            <div>
              <h3 className="font-bold mb-4">Support</h3>
              <ul className="space-y-3">
                <li><a href="#" className="text-gray-300 hover:text-white transition">FAQs</a></li>
                <li><a href="#" className="text-gray-300 hover:text-white transition">Help Center</a></li>
                <li><a href="#" className="text-gray-300 hover:text-white transition">Refund Policy</a></li>
                <li><a href="#" className="text-gray-300 hover:text-white transition">Terms & Conditions</a></li>
              </ul>
            </div>
            <div>
              <h3 className="font-bold mb-4">Connect</h3>
              <div className="flex space-x-4 mb-4">
                <a href="#" className="bg-white/10 hover:bg-white/20 p-2 rounded-full transition">
                  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="h-5 w-5"><path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"></path></svg>
                </a>
                <a href="#" className="bg-white/10 hover:bg-white/20 p-2 rounded-full transition">
                  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="h-5 w-5"><path d="M22 4s-.7 2.1-2 3.4c1.6 10-9.4 17.3-18 11.6 2.2.1 4.4-.6 6-2C3 15.5.5 9.6 3 5c2.2 2.6 5.6 4.1 9 4-.9-4.2 4-6.6 7-3.8 1.1 0 3-1.2 3-1.2z"></path></svg>
                </a>
                <a href="#" className="bg-white/10 hover:bg-white/20 p-2 rounded-full transition">
                  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="h-5 w-5"><rect x="2" y="2" width="20" height="20" rx="5" ry="5"></rect><path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"></path><line x1="17.5" y1="6.5" x2="17.51" y2="6.5"></line></svg>
                </a>
              </div>
              <p className="text-gray-400 text-sm">Contact Us:</p>
              <p className="text-gray-300 text-sm">+91 1800-123-4567</p>
              <p className="text-gray-300 text-sm">support@cloudjet.com</p>
            </div>
          </div>
          <div className="mt-12 border-t border-gray-800 pt-8 text-center">
            <p className="text-gray-400">© 2025 Cloud Jet Services. All rights reserved.</p>
          </div>
        </div>
      </footer>
    </div>
  );
}
