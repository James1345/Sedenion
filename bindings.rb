# bindings.rb

# Loads the required java classes. Adds to some of them to add operator overloading and other convinience methods
# Note additional functionality in this file should be limited to convinience methods, extra functions should be defined in the correct class

require 'java'
require './alexandria'

Parser = Java::com::alexandria::euclid::Parser
REPL =  Java::com::alexandria::euclid::REPL

Complex = Java::com::alexandria::math::Complex
Matrix = Java::com::alexandria::math::Matrix
Vector2D = Java::com::alexandria::math::Vector2D
Vector3D = Java::com::alexandria::math::Vector3D

#Matrix class
class Matrix
	
	#fix it to correctly handle syntax errors
	def +(x)
		if(x.class == Matrix)
			return self.add(x)
		else
			return nil
		end
	end

	def -(x)
		if(x.class == Matrix)
			return self.subtract(x)
		else
			return nil
		end
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

	#Fix array differences between Java and Ruby in constructor
	def initialize(a, b=nil, c=0)
		if(a.to_i == a) #if a is an int
			b = a if !b #set b if not set
			@content = [][]
			for row in 0...a
				for col in 0...b
					@content[row][col] = c
				end
			end
			@isSquare = (@columns = b) == (@rows = a)
		elsif(a.class == Array && a[0].class == Array) ##if a is an array of arrays
			@content = a #quick hack, whole class needs cleaning, see BDMatrix in abandonned for example.
		else
			return nil
		end
	end

end # Of Matrix class
		
