package com.jpcf.backend.domain

class SlideShowImageService {

    def incrementOrdersGreaterThan(def order) {
        def images = SlideShowImage.findAllByOrderGreaterThan(order)
        
        images.each { image
            image.orderIndex++
            image.save()
        }
    }
    
    def moveUp(def id) {
        def image = SlideShowImage.get(id)
        def images = SlideShowImage.findAllByOrderIndexGreaterThan(image.orderIndex)
        def lastOrderIndex = image.orderIndex + 1
        
        def temp
        temp = image.orderIndex
        image.orderIndex = images[0].orderIndex
        images[0].orderIndex = temp
        image.save()
        images[0].save()
    }
    
    def moveDown(def id) {
        def image = SlideShowImage.get(id)
        def images = SlideShowImage.findAllByOrderIndexLessThan(image.orderIndex)
        def lastOrderIndex = image.orderIndex - 1
        
        def temp
        temp = image.orderIndex
        image.orderIndex = images[-1].orderIndex
        images[-1].orderIndex = temp
        image.save()
        images[-1].save()
    }
    
    def getNextOrderIndex() {
        def largest = 0
        SlideShowImage.list().orderIndex.each { index ->
            if (index > largest) {
                largest = index
            }
        }
        
        return largest + 1
    }
}
