# bindings.rb

# Loads the required java classes. Adds to some of them to add operator 
# overloading and other convinience methods
# Note additional functionality in this file should be limited to 
# convinience methods, extra functions should be defined in the java for 
# the correct class

require 'java' #java
require './euclid' #euclid maths

#load classes from modules
Parser = Java::com::alexandria::euclid::Parser
REPL =  Java::com::alexandria::euclid::REPL

Complex = Java::com::alexandria::math::Complex
Matrix = Java::com::alexandria::math::Matrix
Vector = Java::com::alexandria::math::Vector
Vector2D = Java::com::alexandria::math::Vector2D
Vector3D = Java::com::alexandria::math::Vector3D

class Matrix
	
	#Addition and subtraction
	alias :+ :add
	alias :- :subtract

	#Unary minus overload
	def -@()
		return self - (self * 2) #0 - self in effect, possibly not the quickest way but effective
	end

	# If x is a matrix, do matrix multiplication
	# Otherwise, treat x as a float and multiply
	def *(x)
		if(x.class == Matrix)
			return self.rightMultiply(x)
		else
			return self.scale(x.to_f)
		end
	end

	#make fixnum etc. work
	def coerce(other)
		return self, other
	end

	#Fix array differences between Java and Ruby in constructor
	#Nasty hack, need to sort whole thing out, see xAbbandonned BDMatrix for general style
	alias :init :initialize
		def initialize(a)
		init(a.to_java(Java::double[])) #Convert 2D array to java array
	end
	
	#make matrix work like array for getting values
	def [](i)
		row = []
		(0...self.columns).each{ |x| row.push(self.getAt(i, x)) } #recreate row as array
		return row
	end

end # Of Matrix class


class Complex
	
	alias :+ :add
	alias :- :subtract
	alias :* :multiply
	#alias :/ :divide
	
	#define unary minus
	def -@()
		return self - (self*2)
	end
	
	#make fixnums etc. work
	def coerce(other)
		return self, other
	end
	
end #of complex class
