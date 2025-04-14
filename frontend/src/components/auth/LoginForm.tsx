import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import {
  Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle,
} from "@/components/ui/card";
import { Plane } from "lucide-react";

export default function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState<{ email?: string; password?: string; general?: string }>({});
  const navigate = useNavigate();

  const validateForm = () => {
    const newErrors: { email?: string; password?: string } = {};

    if (!email) {
      newErrors.email = "Email is required";
    } else if (!/\S+@\S+\.\S+/.test(email)) {
      newErrors.email = "Email is invalid";
    }

    if (!password) {
      newErrors.password = "Password is required";
    } else if (password.length < 6) {
      newErrors.password = "Password must be at least 6 characters";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
  
    console.log("📤 Form submission started");
    console.log("📨 Entered credentials:", { email, password });
  
    if (!validateForm()) {
      console.warn("❌ Validation failed:", errors);
      return;
    }
  
    try {
      const apiUrl = `${import.meta.env.VITE_API_URL}/api/login`;
      console.log("🌐 Sending POST request to:", apiUrl);
  
      const payload = {
        userEmail: email,
        userPassword: password,
      };
      console.log("📦 Payload being sent:", payload);
  
      const res = await fetch(apiUrl, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      });
  
      console.log("📬 Received response:", res.status, res.statusText);
  
      if (!res.ok) {
        let errorData = null;
        try {
          errorData = await res.json();
          console.error("🚨 Server responded with error data:", errorData);
        } catch (parseErr) {
          console.error("🚫 Failed to parse error response:", parseErr);
        }
  
        setErrors({ general: errorData?.message || "Login failed" });
        return;
      }
  
      const data = await res.json();
      console.log("✅ Login successful. Response data:", data);
  
      localStorage.setItem("token", data.token);
      console.log("🔐 Token saved to localStorage");
  
      navigate("/dashboard");
    } catch (err) {
      console.error("🔥 Unexpected fetch error:", err);
      setErrors({ general: "Something went wrong. Please try again." });
    }
  };
  

  return (
    <div className="min-h-screen bg-cover bg-center flex justify-center items-center px-4 py-12"
         style={{ 
           backgroundImage: 'url("https://images.unsplash.com/photo-1559367183-975d410de28e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80")',
           backgroundSize: 'cover',
           backgroundPosition: 'center',
           backgroundRepeat: 'no-repeat',
           position: 'relative'
         }}>
      <div className="absolute inset-0 bg-black opacity-60 z-0"></div>

      <Card className="w-full max-w-md shadow-2xl relative z-10 bg-white/90 backdrop-blur-sm">
        <CardHeader className="space-y-1">
          <div className="flex justify-center mb-4">
            <div className="bg-airline-blue p-3 rounded-full">
              <Plane className="h-8 w-8 text-white animate-float" />
            </div>
          </div>
          <CardTitle className="text-2xl font-bold text-center">Welcome Back</CardTitle>
          <CardDescription className="text-center">
            Enter your credentials to sign in to your account
          </CardDescription>
        </CardHeader>
        <CardContent className="space-y-4">
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="space-y-2">
              <label htmlFor="email" className="text-sm font-medium">Email</label>
              <Input
                id="email"
                type="email"
                placeholder="Enter your email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className={errors.email ? "border-red-500" : ""}
              />
              {errors.email && <p className="text-red-500 text-sm mt-1">{errors.email}</p>}
            </div>

            <div className="space-y-2">
              <div className="flex items-center justify-between">
                <label htmlFor="password" className="text-sm font-medium">Password</label>
                <Link to="/forgot-password" className="text-xs text-airline-blue hover:underline">
                  Forgot Password?
                </Link>
              </div>
              <Input
                id="password"
                type="password"
                placeholder="Enter your password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className={errors.password ? "border-red-500" : ""}
              />
              {errors.password && <p className="text-red-500 text-sm mt-1">{errors.password}</p>}
            </div>

            {errors.general && (
              <p className="text-red-500 text-sm text-center">{errors.general}</p>
            )}

            <Button type="submit" className="w-full bg-airline-blue hover:bg-airline-navy">
              Sign In
            </Button>
          </form>
        </CardContent> 
        <CardFooter>
          <p className="text-center text-sm text-gray-600 mt-2 w-full">
            Don't have an account?{" "}
            <Link to="/register" className="text-airline-blue hover:underline font-medium">
              Sign Up
            </Link>
          </p>
        </CardFooter>
      </Card>
    </div>
  );
}
