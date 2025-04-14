
import React from 'react';
import Header from "@/components/layout/Header";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Phone, Mail, MapPin, MessageSquare, Send, Check } from "lucide-react";
import { useState } from "react";
import { useToast } from "@/hooks/use-toast";

const Contact = () => {
  const [formSubmitted, setFormSubmitted] = useState(false);
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    subject: "",
    message: ""
  });
  
  const { toast } = useToast();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    // Simulate form submission
    setTimeout(() => {
      toast({
        title: "Message Sent!",
        description: "We've received your message and will get back to you soon.",
        duration: 3000,
      });
      
      setFormSubmitted(true);
      setFormData({
        name: "",
        email: "",
        subject: "",
        message: ""
      });
      
      // Reset after showing success message
      setTimeout(() => setFormSubmitted(false), 3000);
    }, 1000);
  };

  return (
    <div className="min-h-screen flex flex-col">
      <Header />
      
      {/* Hero Section */}
      <section className="bg-gradient-to-b from-sky-100 to-white py-20">
        <div className="container px-4 md:px-6">
          <div className="max-w-3xl mx-auto text-center">
            <h1 className="text-4xl font-extrabold tracking-tighter sm:text-5xl md:text-6xl text-airline-blue">Contact Us</h1>
            <p className="mt-6 text-lg text-gray-600 max-w-2xl mx-auto">
              Have questions or feedback? Our team is here to help you with anything you need.
            </p>
          </div>
        </div>
      </section>
      
      {/* Contact Information */}
      <section className="py-16 bg-white">
        <div className="container px-4 md:px-6">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div className="bg-airline-blue/5 p-8 rounded-2xl text-center">
              <div className="mx-auto bg-airline-blue/10 p-3 rounded-full w-14 h-14 flex items-center justify-center mb-4">
                <Phone className="h-7 w-7 text-airline-blue" />
              </div>
              <h3 className="font-bold text-xl mb-2">Call Us</h3>
              <p className="text-gray-600 mb-2">24/7 Customer Service</p>
              <p className="font-medium text-airline-blue">+91 1800-123-4567</p>
            </div>
            
            <div className="bg-airline-blue/5 p-8 rounded-2xl text-center">
              <div className="mx-auto bg-airline-blue/10 p-3 rounded-full w-14 h-14 flex items-center justify-center mb-4">
                <Mail className="h-7 w-7 text-airline-blue" />
              </div>
              <h3 className="font-bold text-xl mb-2">Email Us</h3>
              <p className="text-gray-600 mb-2">We'll respond within 24 hours</p>
              <p className="font-medium text-airline-blue">support@cloudjet.com</p>
            </div>
            
            <div className="bg-airline-blue/5 p-8 rounded-2xl text-center">
              <div className="mx-auto bg-airline-blue/10 p-3 rounded-full w-14 h-14 flex items-center justify-center mb-4">
                <MapPin className="h-7 w-7 text-airline-blue" />
              </div>
              <h3 className="font-bold text-xl mb-2">Visit Us</h3>
              <p className="text-gray-600 mb-2">Corporate Headquarters</p>
              <p className="font-medium">Cloud Jet Tower, Mumbai, 400001</p>
            </div>
          </div>
        </div>
      </section>
      
      {/* Contact Form */}
      <section className="py-16 bg-gray-50">
        <div className="container px-4 md:px-6">
          <div className="max-w-4xl mx-auto">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-12">
              <div>
                <h2 className="text-3xl font-bold mb-6">Get in Touch</h2>
                <p className="text-gray-600 mb-6">
                  Whether you have a question about flights, bookings, or anything else, our team is ready to answer all your questions.
                </p>
                <div className="space-y-6">
                  <div className="flex items-start gap-4">
                    <MessageSquare className="h-6 w-6 text-airline-blue mt-1" />
                    <div>
                      <h3 className="font-medium">Booking Inquiries</h3>
                      <p className="text-gray-600">For assistance with bookings, schedules, or special requests.</p>
                    </div>
                  </div>
                  <div className="flex items-start gap-4">
                    <MessageSquare className="h-6 w-6 text-airline-blue mt-1" />
                    <div>
                      <h3 className="font-medium">Corporate Travel</h3>
                      <p className="text-gray-600">For information on our corporate travel programs and rates.</p>
                    </div>
                  </div>
                  <div className="flex items-start gap-4">
                    <MessageSquare className="h-6 w-6 text-airline-blue mt-1" />
                    <div>
                      <h3 className="font-medium">Feedback & Suggestions</h3>
                      <p className="text-gray-600">Share your experience or ideas to help us improve.</p>
                    </div>
                  </div>
                </div>
              </div>
              
              <div className="bg-white p-8 rounded-2xl shadow-sm border border-gray-200">
                <h3 className="text-xl font-bold mb-6">Send us a Message</h3>
                
                {formSubmitted ? (
                  <div className="flex flex-col items-center justify-center py-8">
                    <div className="bg-green-100 rounded-full p-3">
                      <Check className="h-8 w-8 text-green-600" />
                    </div>
                    <h4 className="text-lg font-medium mt-4">Message Sent!</h4>
                    <p className="text-gray-600 text-center mt-2">
                      Thank you for reaching out. We'll respond to your inquiry as soon as possible.
                    </p>
                  </div>
                ) : (
                  <form onSubmit={handleSubmit} className="space-y-4">
                    <div>
                      <label htmlFor="name" className="block text-sm font-medium text-gray-700 mb-1">
                        Your Name
                      </label>
                      <Input 
                        id="name" 
                        name="name" 
                        placeholder="Enter your name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                      />
                    </div>
                    
                    <div>
                      <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-1">
                        Email Address
                      </label>
                      <Input 
                        id="email" 
                        name="email" 
                        type="email" 
                        placeholder="Enter your email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                      />
                    </div>
                    
                    <div>
                      <label htmlFor="subject" className="block text-sm font-medium text-gray-700 mb-1">
                        Subject
                      </label>
                      <Input 
                        id="subject" 
                        name="subject" 
                        placeholder="Enter subject"
                        value={formData.subject}
                        onChange={handleChange}
                        required
                      />
                    </div>
                    
                    <div>
                      <label htmlFor="message" className="block text-sm font-medium text-gray-700 mb-1">
                        Message
                      </label>
                      <Textarea 
                        id="message" 
                        name="message" 
                        placeholder="Type your message here..."
                        value={formData.message}
                        onChange={handleChange}
                        rows={4}
                        required
                      />
                    </div>
                    
                    <Button type="submit" className="w-full bg-airline-blue hover:bg-airline-navy">
                      <Send className="mr-2 h-4 w-4" /> Send Message
                    </Button>
                  </form>
                )}
              </div>
            </div>
          </div>
        </div>
      </section>
      
      {/* Map Section */}
      <section className="py-16 bg-white">
        <div className="container px-4 md:px-6">
          <div className="max-w-4xl mx-auto">
            <h2 className="text-2xl font-bold mb-8">Find Our Offices</h2>
            <div className="border rounded-2xl overflow-hidden h-96">
              <iframe 
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d241317.11609823277!2d72.74109995784133!3d19.08219783953142!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3be7c6306644edc1%3A0x5da4ed8f8d648c69!2sMumbai%2C%20Maharashtra!5e0!3m2!1sen!2sin!4v1650016754251!5m2!1sen!2sin" 
                width="100%" 
                height="100%" 
                style={{ border: 0 }} 
                allowFullScreen={false} 
                loading="lazy" 
                referrerPolicy="no-referrer-when-downgrade"
              ></iframe>
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

export default Contact;
