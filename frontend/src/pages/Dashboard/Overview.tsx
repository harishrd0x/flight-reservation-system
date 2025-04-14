
import { useAuth } from "@/context/AuthContext";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { Plane, CreditCard, Calendar, Users } from "lucide-react";

export default function DashboardOverview() {
  const { user } = useAuth();
  const isAdmin = user?.userType === 'admin';

  return (
    <div className="relative min-h-[calc(100vh-80px)]">
      {/* Background image with overlay */}
      <div className="absolute inset-0 -z-10">
        <div 
          className="absolute inset-0 bg-cover bg-center opacity-10"
          style={{ 
            backgroundImage: 'url("https://images.unsplash.com/photo-1464037866556-6812c9d1c72e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80")'
          }}
        ></div>
        <div className="absolute inset-0 bg-gradient-to-r from-white/80 via-white/95 to-white/80 dark:from-gray-900/80 dark:via-gray-900/95 dark:to-gray-900/80"></div>
      </div>

      <div className="space-y-6 relative z-10">
        {/* Welcome banner with blue background */}
        <div className="bg-gradient-to-r from-airline-blue to-airline-navy rounded-xl p-6 shadow-lg mb-8">
          <div className="max-w-4xl mx-auto">
            <h1 className="text-3xl font-bold tracking-tight text-white mb-2">
              Welcome back, {user?.email}!
            </h1>
            <p className="text-blue-100">
              {isAdmin ? 
                'Manage your airline system with ease. Here\'s an overview of your operations.' : 
                'Your flight information at a glance. Manage your bookings and wallet below.'}
            </p>
          </div>
        </div>

        <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-4">
          <Card className="border-none shadow-md hover:shadow-lg transition-shadow duration-300 bg-white/80 backdrop-blur-sm">
            <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
              <CardTitle className="text-sm font-medium">
                {isAdmin ? 'Total Flights' : 'Booked Flights'}
              </CardTitle>
              <div className="rounded-full p-2 bg-blue-100">
                <Plane className="h-4 w-4 text-airline-blue" />
              </div>
            </CardHeader>
            <CardContent>
              <div className="text-2xl font-bold">{isAdmin ? '24' : '2'}</div>
              <p className="text-xs text-muted-foreground mt-1">
                {isAdmin ? '+5 since last month' : 'Next flight in 3 days'}
              </p>
            </CardContent>
          </Card>
          <Card className="border-none shadow-md hover:shadow-lg transition-shadow duration-300 bg-white/80 backdrop-blur-sm">
            <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
              <CardTitle className="text-sm font-medium">
                {isAdmin ? 'Total Bookings' : 'Wallet Balance'}
              </CardTitle>
              <div className="rounded-full p-2 bg-green-100">
                <CreditCard className="h-4 w-4 text-green-600" />
              </div>
            </CardHeader>
            <CardContent>
              <div className="text-2xl font-bold">{isAdmin ? '312' : '₹92,500'}</div>
              <p className="text-xs text-muted-foreground mt-1">
                {isAdmin ? '+18% from last month' : 'Last transaction 2 days ago'}
              </p>
            </CardContent>
          </Card>
          <Card className="border-none shadow-md hover:shadow-lg transition-shadow duration-300 bg-white/80 backdrop-blur-sm">
            <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
              <CardTitle className="text-sm font-medium">
                {isAdmin ? 'Active Planes' : 'Miles Earned'}
              </CardTitle>
              <div className="rounded-full p-2 bg-amber-100">
                <Plane className="h-4 w-4 text-amber-600" />
              </div>
            </CardHeader>
            <CardContent>
              <div className="text-2xl font-bold">{isAdmin ? '18' : '4,320'}</div>
              <p className="text-xs text-muted-foreground mt-1">
                {isAdmin ? '2 in maintenance' : '+1,200 miles this month'}
              </p>
            </CardContent>
          </Card>
          <Card className="border-none shadow-md hover:shadow-lg transition-shadow duration-300 bg-white/80 backdrop-blur-sm">
            <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
              <CardTitle className="text-sm font-medium">
                {isAdmin ? 'Total Customers' : 'Reviews Submitted'}
              </CardTitle>
              <div className="rounded-full p-2 bg-purple-100">
                <Users className="h-4 w-4 text-purple-600" />
              </div>
            </CardHeader>
            <CardContent>
              <div className="text-2xl font-bold">{isAdmin ? '573' : '3'}</div>
              <p className="text-xs text-muted-foreground mt-1">
                {isAdmin ? '+7% from last month' : 'Last reviewed 1 month ago'}
              </p>
            </CardContent>
          </Card>
        </div>

        <div className="grid gap-6 md:grid-cols-2">
          <Card className="border-none shadow-lg bg-white/80 backdrop-blur-sm">
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <div className="h-2 w-2 rounded-full bg-airline-blue animate-pulse"></div>
                {isAdmin ? 'Recent Bookings' : 'Upcoming Flights'}
              </CardTitle>
              <CardDescription>
                {isAdmin ? 'Recent flight bookings across the system.' : 'Your upcoming travel plans.'}
              </CardDescription>
            </CardHeader>
            <CardContent className="space-y-4">
              {isAdmin ? (
                <>
                  <div className="flex justify-between items-center py-3 border-b hover:bg-gray-50 px-2 rounded transition-colors">
                    <div>
                      <p className="font-medium">John Doe</p>
                      <p className="text-sm text-gray-500">CJ-1245 • New Delhi to London</p>
                    </div>
                    <span className="text-sm font-medium">₹31,900</span>
                  </div>
                  <div className="flex justify-between items-center py-3 border-b hover:bg-gray-50 px-2 rounded transition-colors">
                    <div>
                      <p className="font-medium">Jane Smith</p>
                      <p className="text-sm text-gray-500">CJ-3782 • Paris to Dubai</p>
                    </div>
                    <span className="text-sm font-medium">₹53,500</span>
                  </div>
                  <div className="flex justify-between items-center py-3 hover:bg-gray-50 px-2 rounded transition-colors">
                    <div>
                      <p className="font-medium">Alex Johnson</p>
                      <p className="text-sm text-gray-500">CJ-9012 • Tokyo to Sydney</p>
                    </div>
                    <span className="text-sm font-medium">₹63,200</span>
                  </div>
                </>
              ) : (
                <>
                  <div className="flex justify-between items-center py-3 border-b hover:bg-gray-50 px-2 rounded transition-colors">
                    <div>
                      <p className="font-medium">New Delhi to London</p>
                      <p className="text-sm text-gray-500">CJ-1245 • June 15, 2025 • 9:30 AM</p>
                    </div>
                    <span className="bg-blue-100 text-blue-800 text-xs font-medium px-3 py-1 rounded-full">Confirmed</span>
                  </div>
                  <div className="flex justify-between items-center py-3 hover:bg-gray-50 px-2 rounded transition-colors">
                    <div>
                      <p className="font-medium">London to Paris</p>
                      <p className="text-sm text-gray-500">CJ-2347 • June 22, 2025 • 2:15 PM</p>
                    </div>
                    <span className="bg-blue-100 text-blue-800 text-xs font-medium px-3 py-1 rounded-full">Confirmed</span>
                  </div>
                </>
              )}
            </CardContent>
          </Card>
          <Card className="border-none shadow-lg bg-white/80 backdrop-blur-sm">
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <div className="h-2 w-2 rounded-full bg-airline-blue animate-pulse"></div>
                {isAdmin ? 'Customer Reviews' : 'Wallet Transactions'}
              </CardTitle>
              <CardDescription>
                {isAdmin ? 'Recent customer feedback received.' : 'Recent transactions from your wallet.'}
              </CardDescription>
            </CardHeader>
            <CardContent className="space-y-4">
              {isAdmin ? (
                <>
                  <div className="flex justify-between items-center py-3 border-b hover:bg-gray-50 px-2 rounded transition-colors">
                    <div>
                      <div className="flex items-center">
                        <p className="font-medium">Emily Williams</p>
                        <div className="flex ml-2">
                          {[...Array(5)].map((_, i) => (
                            <svg key={i} className="w-4 h-4 text-yellow-300" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 22 20">
                              <path d="M20.924 7.625a1.523 1.523 0 0 0-1.238-1.044l-5.051-.734-2.259-4.577a1.534 1.534 0 0 0-2.752 0L7.365 5.847l-5.051.734A1.535 1.535 0 0 0 1.463 9.2l3.656 3.563-.863 5.031a1.532 1.532 0 0 0 2.226 1.616L11 17.033l4.518 2.375a1.534 1.534 0 0 0 2.226-1.617l-.863-5.03L20.537 9.2a1.523 1.523 0 0 0 .387-1.575Z"/>
                            </svg>
                          ))}
                        </div>
                      </div>
                      <p className="text-sm text-gray-500">"Excellent service and comfortable flight!"</p>
                    </div>
                  </div>
                  <div className="flex justify-between items-center py-3 hover:bg-gray-50 px-2 rounded transition-colors">
                    <div>
                      <div className="flex items-center">
                        <p className="font-medium">Michael Brown</p>
                        <div className="flex ml-2">
                          {[...Array(4)].map((_, i) => (
                            <svg key={i} className="w-4 h-4 text-yellow-300" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 22 20">
                              <path d="M20.924 7.625a1.523 1.523 0 0 0-1.238-1.044l-5.051-.734-2.259-4.577a1.534 1.534 0 0 0-2.752 0L7.365 5.847l-5.051.734A1.535 1.535 0 0 0 1.463 9.2l3.656 3.563-.863 5.031a1.532 1.532 0 0 0 2.226 1.616L11 17.033l4.518 2.375a1.534 1.534 0 0 0 2.226-1.617l-.863-5.03L20.537 9.2a1.523 1.523 0 0 0 .387-1.575Z"/>
                            </svg>
                          ))}
                        </div>
                      </div>
                      <p className="text-sm text-gray-500">"Good experience but the meal could be better."</p>
                    </div>
                  </div>
                </>
              ) : (
                <>
                  <div className="flex justify-between items-center py-3 border-b hover:bg-gray-50 px-2 rounded transition-colors">
                    <div>
                      <p className="font-medium">Flight Booking - CJ-1245</p>
                      <p className="text-sm text-gray-500">June 2, 2025</p>
                    </div>
                    <span className="text-red-500 font-medium">-₹31,900</span>
                  </div>
                  <div className="flex justify-between items-center py-3 border-b hover:bg-gray-50 px-2 rounded transition-colors">
                    <div>
                      <p className="font-medium">Wallet Top-up</p>
                      <p className="text-sm text-gray-500">May 28, 2025</p>
                    </div>
                    <span className="text-green-500 font-medium">+₹37,000</span>
                  </div>
                  <div className="flex justify-between items-center py-3 hover:bg-gray-50 px-2 rounded transition-colors">
                    <div>
                      <p className="font-medium">Miles Conversion</p>
                      <p className="text-sm text-gray-500">May 15, 2025</p>
                    </div>
                    <span className="text-green-500 font-medium">+₹8,900</span>
                  </div>
                </>
              )}
            </CardContent>
          </Card>
        </div>
      </div>
    </div>
  );
}
