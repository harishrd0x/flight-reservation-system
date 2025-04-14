
import React from 'react';
import Header from "@/components/layout/Header";
import { Plane, Clock, Shield, Users, Award, Check } from "lucide-react";

const AboutUs = () => {
  return (
    <div className="min-h-screen flex flex-col">
      <Header />
      
      {/* Hero Section */}
      <section className="bg-gradient-to-b from-sky-100 to-white py-20">
        <div className="container px-4 md:px-6">
          <div className="max-w-3xl mx-auto text-center">
            <h1 className="text-4xl font-extrabold tracking-tighter sm:text-5xl md:text-6xl text-airline-blue">About Cloud Jet Services</h1>
            <p className="mt-6 text-lg text-gray-600 max-w-2xl mx-auto">
              India's premier airline committed to safety, comfort, and exceptional service across domestic and international destinations.
            </p>
          </div>
        </div>
      </section>
      
      {/* Our Story */}
      <section className="py-16 bg-white">
        <div className="container px-4 md:px-6">
          <div className="grid gap-12 md:grid-cols-2 items-center">
            <div>
              <h2 className="text-3xl font-bold tracking-tight mb-6">Our Story</h2>
              <p className="text-gray-600 mb-6">
                Founded in 2015, Cloud Jet Services began with a vision to transform air travel in India. Starting with just 5 aircraft and serving 10 destinations, we've grown into one of the country's leading airlines.
              </p>
              <p className="text-gray-600 mb-6">
                Our journey has been defined by a commitment to innovation, safety, and customer satisfaction. We've consistently invested in modern aircraft, advanced technology, and comprehensive training for our staff.
              </p>
              <p className="text-gray-600">
                Today, we connect millions of passengers to over 100 destinations across India and internationally, maintaining our founding principles of reliability, comfort, and value.
              </p>
            </div>
            <div className="relative">
              <img 
                src="https://images.unsplash.com/photo-1521727857535-32041102b29a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80" 
                alt="Cloud Jet aircraft" 
                className="rounded-2xl shadow-xl object-cover"
              />
              <div className="absolute -bottom-6 -right-6 bg-white p-4 rounded-xl shadow-lg">
                <p className="font-bold text-airline-blue">Est. 2015</p>
                <p className="text-sm text-gray-500">10+ Years of Excellence</p>
              </div>
            </div>
          </div>
        </div>
      </section>
      
      {/* Our Values */}
      <section className="py-16 bg-gray-50">
        <div className="container px-4 md:px-6">
          <div className="text-center mb-12">
            <h2 className="text-3xl font-bold tracking-tight">Our Core Values</h2>
            <p className="mt-4 text-gray-600 max-w-2xl mx-auto">
              These principles guide every decision we make and every service we provide.
            </p>
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div className="bg-white p-8 rounded-2xl shadow-sm border border-gray-100">
              <div className="bg-airline-blue/10 p-3 rounded-full w-12 h-12 flex items-center justify-center mb-4">
                <Shield className="h-6 w-6 text-airline-blue" />
              </div>
              <h3 className="text-xl font-bold mb-3">Safety First</h3>
              <p className="text-gray-600">
                The safety of our passengers and crew is our utmost priority. Our aircraft undergo rigorous maintenance checks, and our staff receive continuous training on safety protocols.
              </p>
            </div>
            
            <div className="bg-white p-8 rounded-2xl shadow-sm border border-gray-100">
              <div className="bg-airline-blue/10 p-3 rounded-full w-12 h-12 flex items-center justify-center mb-4">
                <Clock className="h-6 w-6 text-airline-blue" />
              </div>
              <h3 className="text-xl font-bold mb-3">Reliability</h3>
              <p className="text-gray-600">
                We pride ourselves on our punctuality and dependability. We understand the value of your time and strive to maintain schedules with maximum efficiency.
              </p>
            </div>
            
            <div className="bg-white p-8 rounded-2xl shadow-sm border border-gray-100">
              <div className="bg-airline-blue/10 p-3 rounded-full w-12 h-12 flex items-center justify-center mb-4">
                <Users className="h-6 w-6 text-airline-blue" />
              </div>
              <h3 className="text-xl font-bold mb-3">Customer Focus</h3>
              <p className="text-gray-600">
                Our passengers are at the heart of everything we do. We continuously listen to feedback and adapt our services to meet and exceed expectations.
              </p>
            </div>
          </div>
        </div>
      </section>
      
      {/* Our Fleet */}
      <section className="py-16 bg-white">
        <div className="container px-4 md:px-6">
          <div className="text-center mb-12">
            <h2 className="text-3xl font-bold tracking-tight">Our Modern Fleet</h2>
            <p className="mt-4 text-gray-600 max-w-2xl mx-auto">
              We operate a diverse fleet of modern aircraft to serve various routes efficiently.
            </p>
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
            <div className="border rounded-2xl overflow-hidden">
              <img 
                src="https://images.unsplash.com/photo-1556388158-158ea5ccacbd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2000&q=80" 
                alt="Boeing 737" 
                className="w-full h-48 object-cover"
              />
              <div className="p-6">
                <h3 className="font-bold text-lg mb-2">Boeing 737-800</h3>
                <div className="flex justify-between text-sm text-gray-600 mb-4">
                  <span>Capacity: 189 passengers</span>
                  <span>Range: 5,765 km</span>
                </div>
                <p className="text-gray-600">
                  Our primary aircraft for domestic routes, offering comfort and efficiency.
                </p>
              </div>
            </div>
            
            <div className="border rounded-2xl overflow-hidden">
              <img 
                src="https://images.unsplash.com/photo-1474302770737-173ee21bab63?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2036&q=80" 
                alt="Airbus A320" 
                className="w-full h-48 object-cover"
              />
              <div className="p-6">
                <h3 className="font-bold text-lg mb-2">Airbus A320neo</h3>
                <div className="flex justify-between text-sm text-gray-600 mb-4">
                  <span>Capacity: 180 passengers</span>
                  <span>Range: 6,500 km</span>
                </div>
                <p className="text-gray-600">
                  Fuel-efficient aircraft used for high-demand domestic and short international routes.
                </p>
              </div>
            </div>
            
            <div className="border rounded-2xl overflow-hidden">
              <img 
                src="https://images.unsplash.com/photo-1540339832862-474599807836?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80" 
                alt="Boeing 787 Dreamliner" 
                className="w-full h-48 object-cover"
              />
              <div className="p-6">
                <h3 className="font-bold text-lg mb-2">Boeing 787 Dreamliner</h3>
                <div className="flex justify-between text-sm text-gray-600 mb-4">
                  <span>Capacity: 280 passengers</span>
                  <span>Range: 14,140 km</span>
                </div>
                <p className="text-gray-600">
                  Our flagship aircraft for international long-haul flights, offering premium comfort.
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>
      
      {/* Awards & Recognition */}
      <section className="py-16 bg-gray-50">
        <div className="container px-4 md:px-6">
          <div className="text-center mb-12">
            <h2 className="text-3xl font-bold tracking-tight">Awards & Recognition</h2>
            <p className="mt-4 text-gray-600 max-w-2xl mx-auto">
              Our commitment to excellence has been recognized throughout the industry.
            </p>
          </div>
          
          <div className="max-w-4xl mx-auto grid grid-cols-1 md:grid-cols-2 gap-8">
            <div className="flex gap-4 items-start">
              <div className="bg-airline-blue/10 p-2 rounded-full">
                <Award className="h-6 w-6 text-airline-blue" />
              </div>
              <div>
                <h3 className="font-bold text-lg">Best Domestic Airline 2023</h3>
                <p className="text-gray-600">Awarded by Travel & Tourism Council of India</p>
              </div>
            </div>
            
            <div className="flex gap-4 items-start">
              <div className="bg-airline-blue/10 p-2 rounded-full">
                <Award className="h-6 w-6 text-airline-blue" />
              </div>
              <div>
                <h3 className="font-bold text-lg">Customer Service Excellence</h3>
                <p className="text-gray-600">Skytrax World Airline Awards 2022</p>
              </div>
            </div>
            
            <div className="flex gap-4 items-start">
              <div className="bg-airline-blue/10 p-2 rounded-full">
                <Award className="h-6 w-6 text-airline-blue" />
              </div>
              <div>
                <h3 className="font-bold text-lg">Best On-Time Performance</h3>
                <p className="text-gray-600">Flight Stats On-Time Performance Service Awards 2023</p>
              </div>
            </div>
            
            <div className="flex gap-4 items-start">
              <div className="bg-airline-blue/10 p-2 rounded-full">
                <Award className="h-6 w-6 text-airline-blue" />
              </div>
              <div>
                <h3 className="font-bold text-lg">5-Star Safety Rating</h3>
                <p className="text-gray-600">Airline Ratings Safety Assessment 2023</p>
              </div>
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
                <li><a href="#" className="text-gray-300 hover:text-white transition">About Us</a></li>
                <li><a href="#" className="text-gray-300 hover:text-white transition">Careers</a></li>
                <li><a href="#" className="text-gray-300 hover:text-white transition">Contact</a></li>
                <li><a href="#" className="text-gray-300 hover:text-white transition">Press</a></li>
              </ul>
            </div>
            <div>
              <h3 className="font-bold mb-4">Services</h3>
              <ul className="space-y-3">
                <li><a href="#" className="text-gray-300 hover:text-white transition">Domestic Flights</a></li>
                <li><a href="#" className="text-gray-300 hover:text-white transition">International Flights</a></li>
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
};

export default AboutUs;
