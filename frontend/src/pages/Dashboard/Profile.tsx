
import React, { useState } from 'react';
import { useAuth } from '@/context/AuthContext';
import { 
  Card, 
  CardContent, 
  CardDescription, 
  CardFooter, 
  CardHeader, 
  CardTitle 
} from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Textarea } from '@/components/ui/textarea';
import { Label } from '@/components/ui/label';
import { toast } from '@/components/ui/use-toast';
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { UserProfile } from '@/types/user';
import { Mail, Phone, MapPin, User, Save, Edit, UploadCloud } from 'lucide-react';

export default function Profile() {
  const { userProfile, updateUserProfile } = useAuth();
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState<Partial<UserProfile>>(userProfile || {});

  if (!userProfile) {
    return (
      <div className="flex items-center justify-center h-full">
        <p>Please log in to view your profile</p>
      </div>
    );
  }

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleAvatarChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setFormData({
          ...formData,
          avatarUrl: reader.result as string,
        });
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    updateUserProfile(formData);
    setIsEditing(false);
    toast({
      title: "Profile updated",
      description: "Your profile has been updated successfully",
    });
  };

  const toggleEdit = () => {
    if (isEditing) {
      // Reset form data if canceling edit
      setFormData(userProfile);
    }
    setIsEditing(!isEditing);
  };

  // Generate avatar fallback from email or display name
  const getAvatarFallback = () => {
    if (userProfile.displayName) {
      return userProfile.displayName.split(' ').map(n => n[0]).join('').toUpperCase();
    }
    return userProfile.email.substring(0, 2).toUpperCase();
  };

  return (
    <div className="container py-10 mx-auto">
      <h1 className="text-3xl font-bold mb-8">Your Profile</h1>
      
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {/* Profile Card */}
        <Card className="md:col-span-1">
          <CardHeader className="text-center">
            <div className="flex justify-center mb-4">
              <Avatar className="h-24 w-24">
                <AvatarImage src={userProfile.avatarUrl} />
                <AvatarFallback className="text-xl">{getAvatarFallback()}</AvatarFallback>
              </Avatar>
            </div>
            <CardTitle>{userProfile.displayName || userProfile.email}</CardTitle>
            <CardDescription className="capitalize">{userProfile.userType} Account</CardDescription>
          </CardHeader>
          <CardContent className="space-y-4">
            <div className="flex items-center gap-2">
              <Mail className="h-4 w-4 text-gray-500" />
              <span>{userProfile.email}</span>
            </div>
            {userProfile.phoneNumber && (
              <div className="flex items-center gap-2">
                <Phone className="h-4 w-4 text-gray-500" />
                <span>{userProfile.phoneNumber}</span>
              </div>
            )}
            {(userProfile.address || userProfile.city) && (
              <div className="flex items-center gap-2">
                <MapPin className="h-4 w-4 text-gray-500" />
                <span>
                  {[userProfile.address, userProfile.city, userProfile.state, userProfile.zipCode]
                    .filter(Boolean)
                    .join(', ')}
                </span>
              </div>
            )}
          </CardContent>
          <CardFooter>
            <Button 
              onClick={toggleEdit} 
              variant={isEditing ? "outline" : "default"} 
              className="w-full"
            >
              {isEditing ? (
                <>Cancel Editing</>
              ) : (
                <><Edit className="mr-2 h-4 w-4" /> Edit Profile</>
              )}
            </Button>
          </CardFooter>
        </Card>

        {/* Edit Form */}
        <Card className="md:col-span-2">
          <CardHeader>
            <CardTitle>{isEditing ? 'Edit Your Profile' : 'Profile Information'}</CardTitle>
            <CardDescription>
              {isEditing 
                ? 'Update your personal information below' 
                : 'Your personal details are displayed below'}
            </CardDescription>
          </CardHeader>
          <CardContent>
            <form onSubmit={handleSubmit} className="space-y-6">
              {isEditing && (
                <div className="space-y-2">
                  <Label htmlFor="avatar" className="block">Profile Picture</Label>
                  <div className="flex items-center gap-4">
                    <Avatar className="h-16 w-16">
                      <AvatarImage src={formData.avatarUrl} />
                      <AvatarFallback>{getAvatarFallback()}</AvatarFallback>
                    </Avatar>
                    <Label 
                      htmlFor="avatar-upload" 
                      className="cursor-pointer flex items-center px-4 py-2 bg-muted hover:bg-muted/80 rounded-md text-sm font-medium"
                    >
                      <UploadCloud className="mr-2 h-4 w-4" />
                      Upload Image
                    </Label>
                    <Input 
                      id="avatar-upload" 
                      type="file" 
                      accept="image/*" 
                      className="hidden" 
                      onChange={handleAvatarChange}
                    />
                  </div>
                </div>
              )}

              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="space-y-2">
                  <Label htmlFor="displayName">Display Name</Label>
                  {isEditing ? (
                    <Input
                      id="displayName"
                      name="displayName"
                      value={formData.displayName || ''}
                      onChange={handleInputChange}
                      placeholder="Enter your full name"
                    />
                  ) : (
                    <p className="text-gray-700 py-2">{userProfile.displayName || 'Not set'}</p>
                  )}
                </div>

                <div className="space-y-2">
                  <Label htmlFor="email">Email</Label>
                  <p className="text-gray-700 py-2">{userProfile.email}</p>
                </div>

                <div className="space-y-2">
                  <Label htmlFor="phoneNumber">Phone Number</Label>
                  {isEditing ? (
                    <Input
                      id="phoneNumber"
                      name="phoneNumber"
                      value={formData.phoneNumber || ''}
                      onChange={handleInputChange}
                      placeholder="Enter your phone number"
                    />
                  ) : (
                    <p className="text-gray-700 py-2">{userProfile.phoneNumber || 'Not set'}</p>
                  )}
                </div>

                <div className="space-y-2">
                  <Label htmlFor="address">Address</Label>
                  {isEditing ? (
                    <Input
                      id="address"
                      name="address"
                      value={formData.address || ''}
                      onChange={handleInputChange}
                      placeholder="Enter your address"
                    />
                  ) : (
                    <p className="text-gray-700 py-2">{userProfile.address || 'Not set'}</p>
                  )}
                </div>

                <div className="space-y-2">
                  <Label htmlFor="city">City</Label>
                  {isEditing ? (
                    <Input
                      id="city"
                      name="city"
                      value={formData.city || ''}
                      onChange={handleInputChange}
                      placeholder="Enter your city"
                    />
                  ) : (
                    <p className="text-gray-700 py-2">{userProfile.city || 'Not set'}</p>
                  )}
                </div>

                <div className="space-y-2">
                  <Label htmlFor="state">State</Label>
                  {isEditing ? (
                    <Input
                      id="state"
                      name="state"
                      value={formData.state || ''}
                      onChange={handleInputChange}
                      placeholder="Enter your state"
                    />
                  ) : (
                    <p className="text-gray-700 py-2">{userProfile.state || 'Not set'}</p>
                  )}
                </div>

                <div className="space-y-2">
                  <Label htmlFor="zipCode">Zip Code</Label>
                  {isEditing ? (
                    <Input
                      id="zipCode"
                      name="zipCode"
                      value={formData.zipCode || ''}
                      onChange={handleInputChange}
                      placeholder="Enter your zip code"
                    />
                  ) : (
                    <p className="text-gray-700 py-2">{userProfile.zipCode || 'Not set'}</p>
                  )}
                </div>
              </div>

              <div className="space-y-2">
                <Label htmlFor="bio">Bio</Label>
                {isEditing ? (
                  <Textarea
                    id="bio"
                    name="bio"
                    value={formData.bio || ''}
                    onChange={handleInputChange}
                    placeholder="Tell us about yourself"
                    className="min-h-[100px]"
                  />
                ) : (
                  <p className="text-gray-700 py-2">{userProfile.bio || 'No bio available'}</p>
                )}
              </div>

              {isEditing && (
                <Button type="submit" className="w-full">
                  <Save className="mr-2 h-4 w-4" /> Save Changes
                </Button>
              )}
            </form>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
