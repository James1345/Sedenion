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
	#Nasty hack, need to sort whole thing out, see xAbbandonned BDMatrix for general style
	alias :init :initialize
		def initialize(a)
		init(a.to_java(Java::double[])) #Convert 2D array to java array
	end

end # Of Matrix class

#TODO write matching java class
class Complex
	
	#alias :+ :add
	#alias :- :subtract
	#alias :* :multiply
	#alias :/ :divide

end

class Fixnum
#TODO alias methods to be able to work with Complex and Matrix (e.g. *(x))

	def *(x)
		return x.*(self)
	end
end
