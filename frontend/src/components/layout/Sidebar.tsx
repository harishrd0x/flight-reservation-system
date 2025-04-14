
import { useState } from "react";
import { NavLink } from "react-router-dom";
import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import { ScrollArea } from "@/components/ui/scroll-area";
import { ChevronLeft, ChevronRight } from "lucide-react";

interface SidebarProps {
  items: {
    name: string;
    path: string;
    icon: React.ReactNode;
  }[];
}

export default function Sidebar({ items }: SidebarProps) {
  const [collapsed, setCollapsed] = useState(false);

  return (
    <div
      className={cn(
        "relative border-r bg-airline-gray transition-all duration-300",
        collapsed ? "w-16" : "w-64"
      )}
    >
      <div className="h-full">
        <div className="flex h-16 items-center justify-between border-b px-4">
          {!collapsed && <span className="font-medium">Navigation</span>}
          <Button
            variant="ghost"
            size="icon"
            className="ml-auto"
            onClick={() => setCollapsed(!collapsed)}
          >
            {collapsed ? <ChevronRight className="h-4 w-4" /> : <ChevronLeft className="h-4 w-4" />}
          </Button>
        </div>
        <ScrollArea className="h-[calc(100vh-4rem)] px-2">
          <nav className="flex flex-col gap-2 py-4">
            {items.map((item, index) => (
              <NavLink
                key={index}
                to={item.path}
                className={({ isActive }) =>
                  cn(
                    "flex items-center gap-3 rounded-lg px-3 py-2 text-sm font-medium transition-colors",
                    isActive ? "bg-airline-blue text-white" : "hover:bg-airline-lightblue/10 hover:text-airline-blue"
                  )
                }
              >
                {item.icon}
                {!collapsed && <span>{item.name}</span>}
              </NavLink>
            ))}
          </nav>
        </ScrollArea>
      </div>
    </div>
  );
}
